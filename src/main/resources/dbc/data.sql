drop table if exists planets;

create table planets (
    id int auto_increment primary key,
    name varchar(10) not null,
);

insert into planets (id, planet_name, distance_from_sun, one_way_light_time_to_the_sun, length_of_year, planet_type, planet_info, planet_image)
values (1, 'Mercury', 100000, 100.2, 80, 'rocky', 'first planet', 'image');
insert into planets (id, planet_name, distance_from_sun, one_way_light_time_to_the_sun, length_of_year, planet_type, planet_info, planet_image)
values (2, 'Venus', 100000, 100.2, 180, 'rocky', 'second planet', 'image');
insert into planets (id, planet_name, distance_from_sun, one_way_light_time_to_the_sun, length_of_year, planet_type, planet_info, planet_image)
values (3, 'Earth', 100000, 100.2, 365, 'mixed', 'third planet', 'image');
insert into planets (id, planet_name, distance_from_sun, one_way_light_time_to_the_sun, length_of_year, planet_type, planet_info, planet_image)
values (4, 'Mars', 100000, 100.2, 420, 'red', 'fourth planet', 'image');
insert into planets (id, planet_name, distance_from_sun, one_way_light_time_to_the_sun, length_of_year, planet_type, planet_info, planet_image)
values (5, 'Jupiter', 100000, 100.2, 500, 'gas', 'fifth planet', 'image');
insert into planets (id, planet_name, distance_from_sun, one_way_light_time_to_the_sun, length_of_year, planet_type, planet_info, planet_image)
values (6, 'Saturn', 100000, 100.2, 630, 'ring', 'sixth planet', 'image');
insert into planets (id, planet_name, distance_from_sun, one_way_light_time_to_the_sun, length_of_year, planet_type, planet_info, planet_image)
values (7, 'Uranus', 100000, 100.2, 750, 'gas', 'seventh planet', 'image');
insert into planets (id, planet_name, distance_from_sun, one_way_light_time_to_the_sun, length_of_year, planet_type, planet_info, planet_image)
values (8, 'Neptun', 100000, 100.2, 800, 'rocky', 'eighth planet', 'image');
insert into planets (id, planet_name, distance_from_sun, one_way_light_time_to_the_sun, length_of_year, planet_type, planet_info, planet_image)
values (9, 'Pluto', 100000, 100.2, 940, 'dwarf', 'ninth planet', 'image');


insert into planets(planet_name, distance_from_sun, one_way_light_time_to_sun, length_of_year, planet_type, planet_info)
values ( 1, 'ziemia', 10000, 2323, 23, 'Skalista', 'cos nie wiem co')

