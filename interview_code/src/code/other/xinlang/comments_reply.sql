create table if not exists comments_reply
(
    commentId bigint auto_increment,
    userId bigint not null,
	replyuserId bigint not null,
    content text not null,
	status tinyint,
    create_time timestamp default current_timestamp(),
) engine=InnoDB charset = utf8mb4;

select comments_reply.content from comments
left join comments_reply on comments.commentId = comments_reply.commentId
where comments.mircoblogId = 1234567, comments.commentId = 1000;