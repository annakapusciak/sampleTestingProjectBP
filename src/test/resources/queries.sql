-- creation of users table
CREATE TABLE users (
    id int NOT NULL AUTO_INCREMENT,
    email varchar(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_countries FOREIGN KEY (citizenship_country_id))
    REFERENCES countries(id)
);

-- creation of countries table
CREATE TABLE countries (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    iso varchar(3) NOT NULL,
    PRIMARY KEY (id)
);

-- Query to show all countries with more than 1000 users, sorted by user count.
-- The country with the most users should be at the top.
select countries.name as countryName, count(users.id) as userCount
from countries
join users on countries.id = users.citizenship_country_id
group by countries.name
having count(users.id) > 1000
order by count(users.id) desc;
