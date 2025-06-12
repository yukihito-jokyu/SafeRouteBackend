-- データベースが存在しない場合は作成（保険的な意味合い）
CREATE DATABASE IF NOT EXISTS safe_route_db;

-- 明示的に使用するDBを指定（明確にするため）
USE safe_route_db;

-- users テーブルが無い場合のみ作成
CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL
);

-- 初期データを挿入（必要なら）
INSERT INTO users (name) VALUES ('Alice'), ('Bob');
