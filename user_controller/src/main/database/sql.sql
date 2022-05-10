drop database if exists springcloud_blog_0;
create database if not exists springcloud_blog_0 default charset utf8;

drop database if exists springcloud_blog_1;
create database if not exists springcloud_blog_1 default charset utf8;

drop table if exists springcloud_blo_0.blog_user_0;
create table if not exists springcloud_blog_0.blog_user_0 (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists springcloud_blog_0.blog_user_1;
create table if not exists springcloud_blog_0.blog_user_1 (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists springcloud_blog_1.blog_user_0;
create table if not exists springcloud_blog_1.blog_user_0 (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists springcloud_blog_1.blog_user_1;
create table if not exists springcloud_blog_1.blog_user_1 (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;