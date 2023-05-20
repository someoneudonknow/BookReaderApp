-- Admin
-- SET GLOBAL local_infile = 'ON';
-- SHOW VARIABLES LIKE 'local_infile';


insert into userInfo (user_name, user_avatar, user_password, user_phoneNumber,is_manager, manager_id)
values ('admin', null, '111111','0123456789', true, 1);
-- User
insert into userInfo (user_name, user_avatar, user_password, user_phoneNumber)
values ('talahy1',LOAD_FILE('E:\\wallpaper\\4.jpg'), 'talahy1','0123456788');
insert into userInfo (user_name, user_avatar, user_password, user_phoneNumber)
values ('tu', null, '111111','012345678711');
insert into userInfo (user_name, user_avatar, user_password, user_phoneNumber)
values ('talahy3', null, '123456789','0123456786');
insert into userInfo (user_name, user_avatar, user_password, user_phoneNumber)
values ('talahy4', null, '159357789','0123456785');
-- Category list
insert into categoryList (category_name) values ('Action');
insert into categoryList (category_name) values ('Adventure');
insert into categoryList (category_name) values ('Chinese Novel');
insert into categoryList (category_name) values ('Comedy');
insert into categoryList (category_name) values ('Cooking');
insert into categoryList (category_name) values ('Drama');
insert into categoryList (category_name) values ('Fantasy');
insert into categoryList (category_name) values ('Game');
insert into categoryList (category_name) values ('Horror');
insert into categoryList (category_name) values ('Isekai');
insert into categoryList (category_name) values ('Korean Novel');
insert into categoryList (category_name) values ('Martial Arts');
insert into categoryList (category_name) values ('Mecha');
insert into categoryList (category_name) values ('Military');
insert into categoryList (category_name) values ('Mystery');
insert into categoryList (category_name) values ('Oneshot');
insert into categoryList (category_name) values ('Psychological');
insert into categoryList (category_name) values ('Romance');
insert into categoryList (category_name) values ('School Life');
insert into categoryList (category_name) values ('Shounen');
insert into categoryList (category_name) values ('Slice of Life');
insert into categoryList (category_name) values ('Sports');
insert into categoryList (category_name) values ('Tragedy');
-- Book Info
insert into bookInfo (book_name, book_author, book_cover, book_description) 
values ('Test', 'Test', null, 'Test');
insert into bookInfo (book_name, book_author, book_cover, book_description) 
values ('Test1', 'Test1', null, 'Test1');
insert into bookInfo (book_name, book_author, book_cover, book_description) 
values ('Test2', 'Test2', null, 'Test2');
insert into bookInfo (book_name, book_author, book_cover, book_description) 
values ('Test3', 'Test3', null, 'Test3');
insert into bookInfo (book_name, book_author, book_cover, book_description) 
values ('Test4', 'Test4', null, 'Test4');
insert into bookInfo (book_name, book_author, book_cover, book_description) 
values ('Test5', 'Test5', null, 'Test5');
-- Have Category
insert into haveCategory (category_id, book_id) 
values (1, 1);
insert into haveCategory (category_id, book_id) 
values (1, 2);
insert into haveCategory (category_id, book_id) 
values (1, 3);
insert into haveCategory (category_id, book_id) 
values (2, 1);
insert into haveCategory (category_id, book_id) 
values (3, 1);
insert into haveCategory (category_id, book_id) 
values (4, 2);
insert into haveCategory (category_id, book_id) 
values (5, 4);
insert into haveCategory (category_id, book_id) 
values (5, 5);
insert into haveCategory (category_id, book_id) 
values (4, 5);
insert into haveCategory (category_id, book_id) 
values (12, 6);
insert into haveCategory (category_id, book_id) 
values (10, 6);
insert into haveCategory (category_id, book_id) 
values (11, 6);
insert into haveCategory (category_id, book_id) 
values (7, 4);
-- Book Chapter
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 1, 'Test', 1);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 2, 'Test', 1);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 3, 'Test', 1);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 1, 'Test', 2);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 1, 'Test', 3);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 2, 'Test', 3);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 1, 'Test', 4);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 2, 'Test', 4);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 3, 'Test', 4);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 1, 'Test', 5);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 1, 'Test', 6);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 2, 'Test', 6);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 3, 'Test', 6);
insert into bookChapter (chapter_title, chapter_update, chapter_serial, chapter_document,book_id) 
values ('Test',NOW(), 4, 'Test', 6);
-- Book Reading
insert into bookReading (last_read,user_id, chapter_id) 
values (null, 2, 1);
insert into bookReading (last_read,user_id, chapter_id) 
values (null, 2, 3);
insert into bookReading (last_read,user_id, chapter_id) 
values (null, 3, 2);
insert into bookReading (last_read,user_id, chapter_id) 
values (null, 4, 1);
insert into bookReading (last_read,user_id, chapter_id) 
values (NOW(), 5, 1);
insert into bookReading (last_read,user_id, chapter_id) 
values (NOW(), 5, 3);
insert into bookReading (last_read,user_id, chapter_id) 
values (NOW(), 5, 6);
insert into bookReading (last_read,user_id, chapter_id) 
values (NOW(), 1, 3);
insert into bookReading (last_read,user_id, chapter_id) 
values (NOW(), 1, 6);

-- Book Review
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (1, 1, 'Truyen hay lam cac ban oi', 4);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (1, 2, 'Truyen ko hay lam cac ban oi', 3);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (1, 3, 'Truyen hoi te', 2);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (2, 2, 'Rat tuyet', 5);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (2, 3, 'Rat la tuyet', 5);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (3, 2, 'hehe', 5);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (4, 4, 'Truyen hay lam cac ban oi', 4);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (4, 5, 'Truyen ko hay lam cac ban oi', 3);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (4, 6, 'Truyen hoi te', 2);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (5, 5, 'Rat tuyet', 5);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (5, 6, 'Rat la tuyet', 5);
insert into bookReview (user_id,book_id, user_comment, user_rating) 
values (5, 4, 'hehe', 5);
insert into bookReview (user_id,book_id, user_comment,review_date, user_rating) 
values (1, 4, 'Truyen hay lam cac ban oi','2023-05-19', 4);
-- insert into bookReview (user_id,book_id, user_comment,review_date, user_rating) 
-- values (1, 5, 'Truyen hay lam cac ban oi',CURDATE(), 4);
-- Book Saved
insert into bookSaved (user_id,book_id) values (1,1);
insert into bookSaved (user_id,book_id) values (1,2);
insert into bookSaved (user_id,book_id) values (2,1);
insert into bookSaved (user_id,book_id) values (2,2);
insert into bookSaved (user_id,book_id) values (2,3);
insert into bookSaved (user_id,book_id) values (3,1);
insert into bookSaved (user_id,book_id) values (3,2);
insert into bookSaved (user_id,book_id) values (3,5);
insert into bookSaved (user_id,book_id) values (4,5);
insert into bookSaved (user_id,book_id) values (5,5);
insert into bookSaved (user_id,book_id) values (1,5);



