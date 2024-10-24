CREATE DATABASE FlappyBird;
go
USE FlappyBird;
go
CREATE TABLE account (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(32) NOT NULL,
    nickname VARCHAR(50) NOT NULL
    UNIQUE (username)
);
go
CREATE TABLE score (
    account_id INT,
    score INT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account(id)
);
go

-- Thêm dữ liệu ví dụ vào bảng account
INSERT INTO account (username, password, nickname) VALUES
('Hung123', '7c6a180b36896a0a8c02787eeafb0e4c', 'Fi Hung'),
('An1975', '6cb75f652a9b52798eb6cf2201057c73', 'Be Lan')
go

-- Thêm dữ liệu ví dụ vào bảng score
INSERT INTO score (account_id, score) VALUES
(1, 12),
(2, 3)
go