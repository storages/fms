select * from material
delete from material
update material set imgexgflag = 'I'
select * from unit
drop table material
select * from quotation
select max(serialNo) from quotation
update quotation set serialNo = 2 where id='402881fb49e0fc1a0149e108602a0003'
update quotation set effectDate = '2014-11-13' where id='402881e949b3bad70149b3be0fb20000'