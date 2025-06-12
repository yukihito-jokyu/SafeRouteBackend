-- データベースが存在しない場合は作成（保険的な意味合い）
CREATE DATABASE IF NOT EXISTS safe_route_db;

-- 明示的に使用するDBを指定（明確にするため）
USE safe_route_db;

-- 1. ユーザー情報テーブル (users)
CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ユーザー一人ひとりを区別するための番号',
    nickname VARCHAR(255) NOT NULL COMMENT 'アプリの中で使われる、ユーザーの名前',
    email VARCHAR(255) NOT NULL UNIQUE COMMENT 'ログインや連絡に使うメールアドレス',
    password_hash VARCHAR(255) NOT NULL COMMENT 'ログイン時のパスワードを安全な形にしたもの',
    gps_consent_flag TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'GPSを使って良いかどうかの、ユーザーの同意状況 (0:不同意, 1:同意)',
    current_points INT NOT NULL DEFAULT 0 COMMENT 'ユーザーが持っているポイントの数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='ユーザー情報テーブル';

-- 2. 避難ルートテーブル (evacuation_routes)
CREATE TABLE IF NOT EXISTS evacuation_routes (
    route_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '各避難ルートを区別するための番号',
    user_id BIGINT NOT NULL COMMENT 'このルートを作ったユーザーのID',
    route_name VARCHAR(255) NOT NULL COMMENT 'ユーザーが付けた、そのルートの名前',
    start_latitude DECIMAL(9, 6) NOT NULL COMMENT '避難ルートの始点の緯度',
    start_longitude DECIMAL(9, 6) NOT NULL COMMENT '避難ルートの始点の経度',
    end_latitude DECIMAL(9, 6) NOT NULL COMMENT '避難ルートの終点の緯度',
    end_longitude DECIMAL(9, 6) NOT NULL COMMENT '避難ルートの終点の経度',
    geometry_data GEOMETRY NOT NULL COMMENT '地図上の経路を示す情報',
    preview_image_url VARCHAR(255) NULL COMMENT '(任意) ルートの簡易的な地図画像のURL',
    distance DECIMAL(10, 2) COMMENT 'そのルートの全長 (メートル単位など)',
    estimated_time INT COMMENT 'そのルートを通るのにかかるおおよその時間 (分単位など)',
    self_assessed_safety TINYINT COMMENT '作成者自身が感じたルートの安全性 (1～5段階)',
    my_route_flag TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'これがユーザーにとっての「私の主要な避難ルート」かどうか',
    status VARCHAR(50) NOT NULL DEFAULT 'draft' COMMENT 'ルートの状態 (例: ''draft'', ''submitted'', ''approved'', ''rejected'')',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ルート情報が作成された日時',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='避難ルートテーブル';

-- 3. 避難訓練情報テーブル (evacuation_drills)
CREATE TABLE IF NOT EXISTS evacuation_drills (
    drill_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '各避難訓練を区別するための番号',
    drill_name VARCHAR(255) NOT NULL COMMENT '避難訓練のタイトル',
    start_datetime TIMESTAMP NOT NULL COMMENT '訓練がいつ始まるかの日時',
    end_datetime TIMESTAMP NOT NULL COMMENT '訓練がいつ終わるかの日時',
    meeting_place VARCHAR(255) COMMENT '訓練の時に集まる場所',
    meeting_latitude DECIMAL(9, 6) COMMENT '集合場所の緯度',
    meeting_longitude DECIMAL(9, 6) COMMENT '集合場所の経度',
    map_info_url VARCHAR(255) NULL COMMENT '(任意) 訓練場所や関連情報を示す外部地図サービスのURL',
    drill_details TEXT COMMENT 'どんな訓練をするかの詳しい説明',
    target_audience VARCHAR(255) COMMENT 'どんな人が参加対象か',
    items_to_bring TEXT COMMENT '訓練参加に必要な持ち物リスト (JSON形式またはカンマ区切りテキスト)',
    notes TEXT COMMENT '訓練に関する気をつけること',
    max_participants INT COMMENT 'この訓練に参加できる人数の上限',
    status VARCHAR(50) NOT NULL DEFAULT 'scheduled' COMMENT '訓練の状態 (例: ''scheduled'', ''in_progress'', ''completed'', ''cancelled'')',
    drill_type VARCHAR(50) COMMENT '訓練の種類や特性 (例: ''recommended'', ''new'')'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='避難訓練情報テーブル';

-- 4. ユーザー避難訓練参加テーブル (user_drill_attendance)
CREATE TABLE IF NOT EXISTS user_drill_attendance (
    attendance_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ユーザーの各訓練参加記録を区別するための番号',
    user_id BIGINT NOT NULL COMMENT '参加するユーザーのID',
    drill_id BIGINT NOT NULL COMMENT '参加する避難訓練のID',
    registered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ユーザーがこの訓練への参加を申し込んだ日時',
    status VARCHAR(50) NOT NULL DEFAULT 'registered' COMMENT 'ユーザーの参加状態 (例: ''registered'', ''attended'', ''cancelled'', ''absent'')',
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (drill_id) REFERENCES evacuation_drills(drill_id),
    UNIQUE (user_id, drill_id) COMMENT '同一ユーザーが同一訓練に重複登録するのを防ぐ'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='ユーザー避難訓練参加テーブル';

-- 5. 避難所情報テーブル (shelters)
CREATE TABLE IF NOT EXISTS shelters (
    shelter_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '各避難所を区別するための番号',
    shelter_name VARCHAR(255) NOT NULL COMMENT '避難所の名前',
    address VARCHAR(255) COMMENT '避難所の所在地',
    latitude DECIMAL(9, 6) NOT NULL COMMENT '避難所の緯度',
    longitude DECIMAL(9, 6) NOT NULL COMMENT '避難所の経度',
    capacity INT COMMENT 'その避難所が何人まで入れるかの目安'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='避難所情報テーブル';

-- 6. ポイント履歴テーブル (point_history)
CREATE TABLE IF NOT EXISTS point_history (
    history_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '各ポイントの動きを区別するための番号',
    user_id BIGINT NOT NULL COMMENT 'ポイントが増減したユーザーのID',
    points_change INT NOT NULL COMMENT '増減したポイント数 (+で獲得, -で使用)',
    reason_details VARCHAR(255) NOT NULL COMMENT 'ポイント履歴に表示される具体的な理由のテキスト',
    transaction_datetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ポイントが増減した正確な日時',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='ポイント履歴テーブル';

-- 7. 特典マスタテーブル (rewards)
CREATE TABLE IF NOT EXISTS rewards (
    reward_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '各特典を区別するための番号',
    reward_name VARCHAR(255) NOT NULL COMMENT '交換できる特典の名前',
    reward_description TEXT COMMENT '特典の内容に関する詳しい説明',
    required_points INT NOT NULL COMMENT 'この特典を得るために必要なポイント',
    category VARCHAR(100) COMMENT '特典の種類 (例: ''Discount Coupon'', ''Disaster Goods'', ''Donation'')'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='特典マスタテーブル';

-- 1. users テーブルへのサンプルデータ挿入
INSERT INTO users (nickname, email, password_hash, gps_consent_flag, current_points) VALUES
('テストユーザー', 'test-user@example.com', 'hashed_password_string_12345', 1, 100);

-- 2. evacuation_drills テーブルへのサンプルデータ挿入
INSERT INTO evacuation_drills (drill_name, start_datetime, end_datetime, meeting_place, meeting_latitude, meeting_longitude, drill_details, target_audience, max_participants, status, drill_type) VALUES
('地域合同防災訓練2025', '2025-07-20 10:00:00', '2025-07-20 12:00:00', '市役所前広場', 34.6937, 135.5023, '地震と津波を想定した避難訓練です。', '地域住民の方ならどなたでも', 100, 'scheduled', 'recommended');

-- 3. shelters テーブルへのサンプルデータ挿入
INSERT INTO shelters (shelter_name, address, latitude, longitude, capacity) VALUES
('〇〇市立中央小学校', '大阪府大阪市中央区1-2-3', 34.6864, 135.5200, 500);

-- 4. rewards テーブルへのサンプルデータ挿入
INSERT INTO rewards (reward_name, reward_description, required_points, category) VALUES
('非常食セット', '5年保存可能な水とカンパンのセットです。', 50, 'Disaster Goods');

-- 5. evacuation_routes テーブルへのサンプルデータ挿入 (user_id=1 を参照)
INSERT INTO evacuation_routes (user_id, route_name, start_latitude, start_longitude, end_latitude, end_longitude, geometry_data, distance, estimated_time, self_assessed_safety, my_route_flag, status) VALUES
(1, '自宅から中央小学校への避難ルート', 34.6900, 135.5000, 34.6864, 135.5200, ST_GeomFromText('LINESTRING(135.5000 34.6900, 135.5200 34.6864)'), 2500.50, 30, 4, 1, 'approved');

-- 6. user_drill_attendance テーブルへのサンプルデータ挿入 (user_id=1 と drill_id=1 を参照)
INSERT INTO user_drill_attendance (user_id, drill_id, status) VALUES
(1, 1, 'registered');

-- 7. point_history テーブルへのサンプルデータ挿入 (user_id=1 を参照)
INSERT INTO point_history (user_id, points_change, reason_details) VALUES
(1, 10, '避難ルートの新規登録ボーナス');