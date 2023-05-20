CREATE DATABASE IF NOT EXISTS project1;
USE project1;
CREATE TABLE IF NOT EXISTS userInfo(
	user_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50) UNIQUE NOT NULL,
    user_avatar MEDIUMBLOB,
    user_password VARCHAR(100) NOT NULL,
    user_phoneNumber VARCHAR(20) UNIQUE NOT NULL,
    is_manager BOOLEAN NOT NULL DEFAULT FALSE,
    manager_id  INT(10) NOT NULL DEFAULT 1 CHECK(manager_id = 1),
    FOREIGN KEY (manager_id) REFERENCES userInfo(user_id)
);

CREATE TABLE IF NOT EXISTS categoryList(
	category_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS bookInfo(
	book_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    book_name VARCHAR(100) UNIQUE NOT NULL,
    book_author VARCHAR(50) NOT NULL,
    book_cover MEDIUMBLOB,
    book_description TEXT NOT NULL,
    manager_id INT(10) NOT NULL DEFAULT 1 CHECK(manager_id = 1),
    FOREIGN KEY (manager_id) REFERENCES userInfo(manager_id)
);
CREATE TABLE IF NOT EXISTS haveCategory(
	category_id INT(10) NOT NULL,
    book_id INT(10) NOT NULL,
    PRIMARY KEY(category_id,book_id),
    FOREIGN KEY (category_id) REFERENCES categoryList(category_id) ,
    FOREIGN KEY (book_id) REFERENCES bookInfo(book_id) 
);
CREATE TABLE IF NOT EXISTS bookChapter(
	chapter_id INT(20) AUTO_INCREMENT PRIMARY KEY,
    chapter_title VARCHAR(200) NOT NULL,
    chapter_serial INT(10) NOT NULL CHECK(chapter_serial >=0),
    chapter_update DATETIME DEFAULT NOW() NOT NULL CHECK(chapter_update >= SYSDATE()),
    chapter_document TEXT NOT NULL,
	chapter_view INT(10) DEFAULT 0 NOT NULL CHECK(chapter_view >=0),
    book_id INT(10) NOT NULL,
    FOREIGN KEY (book_id) REFERENCES bookInfo(book_id)
);
CREATE TABLE IF NOT EXISTS bookReview (
    user_id INT(10) NOT NULL,
    book_id INT(10) NOT NULL,
    user_comment TEXT,
    review_date DATETIME DEFAULT NOW() CHECK(review_date >= SYSDATE()),
    user_rating TINYINT UNSIGNED CHECK (user_rating >= 1 AND user_rating <= 5),
    PRIMARY KEY (user_id , book_id),
    FOREIGN KEY (user_id)
        REFERENCES userInfo (user_id),
    FOREIGN KEY (book_id)
        REFERENCES bookInfo (book_id)
);
CREATE TABLE IF NOT EXISTS bookReading(
    last_read DATETIME DEFAULT NOW() CHECK(last_read >= SYSDATE()),
	user_id INT(10) NOT NULL,
	chapter_id INT(10) NOT NULL,
    PRIMARY KEY(user_id,chapter_id),
	FOREIGN KEY (user_id) REFERENCES userInfo(user_id) ,
    FOREIGN KEY (chapter_id) REFERENCES bookchapter(chapter_id) 
);
CREATE TABLE IF NOT EXISTS bookSaved(
	user_id INT(10) NOT NULL,
	book_id INT(10) NOT NULL,
    saved_date DATETIME DEFAULT NOW() NOT NULL CHECK(saved_date >= SYSDATE()), 
    PRIMARY KEY(user_id,book_id),
	FOREIGN KEY (user_id) REFERENCES userInfo(user_id) ,
    FOREIGN KEY (book_id) REFERENCES bookInfo(book_id) 
);
-- ALTER TABLE userInfo MODIFY manager_id INT(10) NOT NULL DEFAULT 1;




