CREATE DEFINER=`root`@`localhost` PROCEDURE `readRecently`(IN curr_book_id INT(10),IN curr_user_id INT(10))
BEGIN
SELECT MAX(bc.chapter_serial) AS read_recently FROM project1.bookreading AS br 
JOIN project1.bookchapter AS bc ON br.chapter_id = bc.chapter_id
GROUP BY book_id,user_id HAVING bc.book_id = curr_book_id AND br.user_id = curr_user_id;
END