--User 
INSERT INTO Users(username, password, role, display_name, status_message)
VALUES
('admin', 'admin_pass_hashed', 'ADMIN', '管理者', '管理者アカウントです'),
('tanaka', 'tanaka_pass_hashed', 'USER', '田中 太郎', 'ホール1年目'),
('suzuki', 'suzuki_pass_hashed', 'USER', '鈴木 花子', 'キッチン3年目');

--Attendance
INSERT INTO Attendance(user_id, clock_in, clock_out, work_date)
VALUES 
(2, '2025-06-14 09:00:00', '2025-06-14 18:00:00', '2025-06-14'),
(3, '2025-06-14 09:30:00', '2025-06-14 17:30:00', '2025-06-14');

--DailyReport
INSERT INTO DailyReport(user_id, report_date, content)
VALUES
(2, '2025-06-14', '今日は終日満席をいただき忙しかったが、チームの連携がスムーズでお客様を待たせることが少なかった。'),
(3, '2025-06-14', 'メンバーの調理水準の確認と統一を行った。ホールスタッフとの連携も理想的に実行できた。');

