--テーブルが存在すれば削除する
DROP TABLE IF EXISTS Users;

CREATE TABLE Users(
	--ユーザーID : PK
	id serial PRIMARY KEY, 
	--ユーザーネーム : NotNull
	username varchar(50) UNIQUE NOT NULL,
	--パスワード : NotNull
	password varchar(255) NOT NULL,
	--権限ロール('USER', 'ADMIN')
	role varchar(20) NOT NULL,
	--表示名
	display_name varchar(50),
	--状態チェックコメントなど
	status_message TEXT,
	--アカウント作成日時
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

DROP TABLE IF EXISTS Attendance;
--勤怠テーブル
CREATE TABLE Attendance(
	--勤怠ID
	id serial PRIMARY KEY,
	--ユーザーID : FK
	user_id integer NOT NULL REFERENCES Users(id),
	--出勤時間
	clock_in TIMESTAMP NOT NULL,
	--退勤時間
	clock_out TIMESTAMP,
	--勤務日
	work_date DATE NOT NULL
)

DROP TABLE IF EXISTS DailyReports;
--日報テーブル
CREATE TABLE DailyReports(
	--日報id
	id serial PRIMARY KEY,
	--ユーザーid : FK
	user_id integer NOT NULL REFERENCES Users(id),
	--日付
	report_date DATE NOT NULL,
	--本文
	content TEXT
)