Copy (SELECT  * from  public.select_from_cart_by_period(
	'2018-12-03 19:27:18.985128', 
	'2018-12-03 19:37:18.985128'
)) To 'D:\test.csv' With CSV DELIMITER ';' HEADER;