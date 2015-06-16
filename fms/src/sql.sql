select * from material
select * from scmcoc
delete from material
update material set imgexgflag = 'I'
select * from unit
drop table material
select * from quotation where material='402881fb49cb98400149cb99412f0000' and scmcoc='402881fb479090c501479093052a0000'
																					  402881fb479090c501479093052a000e
select * from parameterSet

alter table OperateLogs alter column msg varchar(1000)
select * from OperateLogs

select max(serialNo) from quotation
update quotation set serialNo = 2 where id='402881fb49e0fc1a0149e108602a0003'
update quotation set effectDate = '2014-11-13' where id='402881e949b3bad70149b3be0fb20000'

select * from appbillitem
select * from appbillHead    R201505072023

select itemNo from PurchaseBill
select * from Purchaseitem
delete PurchaseBill
delete Purchaseitem
delete from acluser where id='402881f24b685286014b685df8140000'
update PurchaseBill set printCount = 0
update Purchaseitem set isbuy = 'false'

select * from bomversion
select * from bomexg
select * from bomimg a 
	inner join bomversion b on a.bomversion = b.id
	inner join bomexg c on b.bomexg = c.id
	where b.versionNo = 1
	
	delete bomimg
	delete bomversion
	
select * from orderhead a left join scmcoc b on a.scmcoc = b.id

delete from orderitem

select * from instorage
select * from outstorage

delete  from instorage
delete  from outstorage where id in('402881e84dfc42b4014dfc43baa70000','402881e84dfc42b4014dfc461afc0001','402881e84dfc42b4014dfc4653310002')
