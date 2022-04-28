
CREATE TYPE  QuestionStatus AS ENUM ('Open', 'Closed', 'OnHold', 'Delete');
CREATE TYPE  accStatus AS ENUM ('ACTIVE','INACTIVE');
CREATE TYPE  QuestionClosingRemark AS ENUM('Duplicate', 'OffTopic', 'TooBroad', 'NotConstructive', 'NotRealQuestion', 'OpinionBased');
create table Account(name varchar(50),password varchar(50),email varchar, status accStatus,phone int,id int,reputation int);
     
create table Question( title varchar,
   description varchar,
  viewCount int, 
   voteCount int, 
   flagCount int ,
   createTime Time,
   closingRemark QuestionClosingRemark,
  status QuestionStatus);

create table Comment(text varchar,  creationTime time  , flagcount int ,votecount int );
create table Answer(answer_text varchar,
  accepted boolean,
   voteCount int ,
   flagCount int ,
   createTime time);

alter table Answer alter column flagCount set default 0; 
alter table Question alter column voteCount set default 0;
alter table Question alter column flagCount set default 0;

alter table Question add constraint titlepk primary key (title);
alter table Answer add column qtitle varchar;
alter table Answer add constraint qtfk foreign key (qtitle)  references Question (title);


