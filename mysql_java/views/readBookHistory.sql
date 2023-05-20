CREATE VIEW `readBookHistory` AS
SELECT bi.*,MAX(last_read),br.user_id FROM project1.bookreading AS br 
JOIN project1.bookchapter AS bc ON br.chapter_id = bc.chapter_id 
JOIN project1.bookinfo AS bi ON bc.book_id = bi.book_id
GROUP BY br.user_id, bi.book_id 
ORDER BY MAX(last_read) DESC 