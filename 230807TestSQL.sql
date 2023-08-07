drop table simple_board;
create table simple_board(
	seq integer primary key auto_increment,
	title varchar(100) not null,
	content varchar(1000) not null,
	writer varchar(20) not null, -- id
	read_count integer default 0,
	create_date datetime not null,
	attatch_password varchar(10),
	attatch_data varchar(100)
);

drop table comment_table;
create table comment_table(
	seq integer primary key auto_increment,
	writer varchar(20) not null,
	content varchar(1000) not null,
	comment_like integer default '0',
    comment_hate integer default '0',
    board_seq integer ,
    comment_seq integer 
);

-- 댓글 쿼리 
select * from comment_table;

-- 댓글 전부 삭제 
delete from comment_table where seq;

insert into comment_table(writer, content, board_seq) values("작성자1","내용1", 1);
insert into comment_table(writer, content, board_seq) values("작성자2","내용2", 1);
insert into comment_table(writer, content, board_seq) values("작성자3","내용3", 2);
insert into comment_table(writer, content, board_seq) values("작성자4","내용4", 2);

SELECT seq FROM simple_board WHERE (title LIKE concat('%', IF("방명"="", null, "방명") ,'%') );

SELECT * FROM simple_board WHERE seq in (SELECT seq FROM simple_board WHERE (title LIKE concat('%', IF("방명"="", null, "방명") ,'%'))) LIMIT 2 OFFSET 0;

SELECT * FROM simple_board WHERE title LIKE concat('%', IF("방명"="", null, "방명") ,'%');

SELECT * from simple_board where seq = 12 LIMIT 2 OFFSET 0;

select * from simple_board where seq = 2;

select * from simple_board;

-- ////// 방명록 쿼리 ///////
-- 1. 전체 방명록 리스트
select * from simple_board;
-- 2. 특정 방명록
select * from simple_board where seq = 1;
-- 3. 방명록 등록
insert into simple_board(title, content, writer, read_count, create_date, attatch_password, attatch_data) values("방명록1","내용1","작성자1",4,now(),"1234","12323.png");
insert into simple_board(title, content, writer, read_count, create_date, attatch_password, attatch_data) values("방명록2","내용2","작성자2",4,now(),"1234","12323.png");
insert into simple_board(title, content, writer, read_count, create_date, attatch_password, attatch_data) values("방명록3","내용3","작성자3",4,now(),"1234","12323.png");
insert into simple_board(title, content, writer, read_count, create_date, attatch_password, attatch_data) values("방명록4","내용4","작성자4",4,now(),"1234","12323.png");
insert into simple_board(title, content, writer, read_count, create_date, attatch_password, attatch_data) values("방명록5","내용5","작성자5",4,now(),"1234","12323.png");

-- 4. 방명록 삭제
delete from simple_board where seq= 1;
-- 5. 방명록 조회
SELECT * FROM simple_board WHERE title LIKE "%2%"  OR content LIKE "% %" OR writer LIKE "% %";

SELECT * FROM simple_board WHERE (title LIKE CONCAT('%', IFNULL(null, ' '), '%'));

-- 6. 조회수 업데이트
update simple_board set read_count = (read_count + 1) where seq = 1;