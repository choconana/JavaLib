create table if not exists comments
(
    commentId bigint auto_increment primary key,
    mricoblogId bigint not null,
    userId bigint not null,
    parentId bigint,
    content text not null,
	state int,
    create_time timestamp default current_timestamp(),
    foreign key (parentID) references comments (commentId),
    foreign key (userID) references users (userId)
) engine=InnoDB charset = utf8mb4;