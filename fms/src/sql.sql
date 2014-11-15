select * from material
delete from material
update material set imgexgflag = 'I'

select * from unit

drop table material

select * from quotation

select max(serialNo) from quotation

update quotation set effectDate = '2014-11-13' where id='402881e949b3bad70149b3be0fb20000'