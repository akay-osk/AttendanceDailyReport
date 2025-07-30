--テーブルが存在すれば削除する
DROP TABLE IF EXISTS Attendances;
DROP TABLE IF EXISTS DailyReports;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users(
	--ユーザーID : PK
	id serial PRIMARY KEY, 
	--ログインID : NotNull
	login_id varchar(20) UNIQUE NOT NULL,
	--ユーザーネーム : NotNull
	name varchar(50) NOT NULL,
	--パスワード : NotNull
	password varchar(100) NOT NULL,
	--権限ロール('USER', 'ADMIN')
	role varchar(20) DEFAULT 'USER',
	--コメントなど
	status_message TEXT,
	--アカウント作成日時
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--勤怠テーブル
CREATE TABLE Attendances(
	--勤怠ID
	id serial PRIMARY KEY,
	--ユーザーID : FK
	user_id integer NOT NULL REFERENCES Users(id),
	--出勤時間
	clock_in TIMESTAMP,
	--退勤時間
	clock_out TIMESTAMP
);

--日報テーブル
CREATE TABLE DailyReports (
	--日報id
	id serial PRIMARY KEY,
	--ユーザーid : FK
	user_id integer NOT NULL REFERENCES Users(id),
	--日付
	report_date DATE NOT NULL,
	--日報のタイトル
	title varchar(100),
	--本文(報告事項)
	content TEXT,
	--印象に残ったこと(感想)
	impression TEXT,
	
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);