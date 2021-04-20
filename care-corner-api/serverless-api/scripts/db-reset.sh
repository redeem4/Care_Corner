#!/bin/bash
echo "SET FOREIGN_KEY_CHECKS = 0;" > ./temp.sql
mysqldump --add-drop-table --no-data -h database -u root -p care-corner | grep 'DROP TABLE' >> ./temp.sql
echo "SET FOREIGN_KEY_CHECKS = 1;" >> ./temp.sql
mysql -u root -h database -p care-corner < ./temp.sql
rm ./temp.sql
mysql -u care -p -h database care-corner < db/entire_db.sql

