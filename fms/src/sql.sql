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

select * from PurchaseBill
select * from Purchaseitem
delete PurchaseBill
delete Purchaseitem
delete from acluser where id='402881f24b685286014b685df8140000'
update PurchaseBill set IsComplete = 'false'
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
update instorage set inqty = 123.0 where orderno = 'OR2015050521522'
select * from outstorage

delete  from instorage
delete  from outstorage where id in('402881e84dfc42b4014dfc43baa70000','402881e84dfc42b4014dfc461afc0001','402881e84dfc42b4014dfc4653310002')

select a.qty from PurchaseItem a left join  purchaseBill b on a.purchaseBill = b.id
 left join  material c on a.material = c.id where c.hsCode ='1013915A' and b.purchaseNo ='C201505122210092' and c.imgExgFlag ='I'
 
 select sum(a.inQty) from InStorage a left join  material b on a.material = b.id where a.orderNo =? and a.imgExgFlag =? and b.hsCode =? and a.impFlag in (1,2)
 
 