CREATE DEFINER=`root`@`localhost` TRIGGER `userinfo_BEFORE_INSERT` BEFORE INSERT ON `userinfo` FOR EACH ROW BEGIN
	SET NEW.manager_id = 1;
END