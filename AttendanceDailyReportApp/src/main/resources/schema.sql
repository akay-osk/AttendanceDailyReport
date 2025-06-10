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
	created_at TIMESTAMP DEFAULT now()
)