CREATE DEFINER=`root`@`localhost` FUNCTION `countChapter`(curr_book_id INT(4)) RETURNS int
    READS SQL DATA
BEGIN
	DECLARE chapterCount INT(4);
    SELECT COUNT(*) INTO chapterCount FROM project1.bookChapter WHERE book_id = curr_book_id;
	RETURN chapterCount;
END