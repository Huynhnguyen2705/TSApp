
create database TS;
create table CustomerType(
-- ID: CusType01 -> CusType 10
	ID varchar(9) primary key,
	typeName nvarchar(20)
);

create table Customer(
-- ID: Cus00001 -> Cus10000
	ID varchar(8) primary key,
	FullName nvarchar(50),
	PhoneNumber varchar(11),
	TotalBill money, 
	CusType varchar(9) FOREIGN KEY REFERENCES CustomerType(ID)
);

create table Paramater(
-- ID: Par001 -> Par100
	ID varchar(6) primary key,
	ParaName nvarchar(30),
	ParaValue int
);

 create table RoleAcc(
 -- ID: Role01 -> Role10
	ID varchar(6) primary key,
	RoleName nvarchar(20)
 ); 

 create table Account(
	UserName varchar(20) primary key,
	Passwrd varchar(30),
	AccRole varchar(6) FOREIGN KEY REFERENCES RoleAcc(ID)
 );

 create table Employee(
 -- ID: Emp001 -> Emp100
	ID varchar(6) primary key,
	EmpFullName nvarchar(40),
	PhoneNumber varchar(11),
	DOB date,
	IDCard varchar(12),
	EmpAddress nvarchar(100),
	UserName varchar(20) FOREIGN KEY REFERENCES Account(UserName),
	Statue tinyint
 );


  create table ProductType(
  -- ID: ProdType001 -> ProdType100
	ID varchar(11) primary key,
	TypeName nvarchar(30)
 );

 create table ProductSize(
	--ID: Size01 -> Siza001
	ID varchar(6) primary key,
	SizeName int
 );
 create table Product(
 -- ID: Prod000001 -> Prod100000
	ID varchar(10) primary key,
	ProductName nvarchar(30),
	Price money,
	--gram
	Size varchar(6) FOREIGN KEY REFERENCES ProdSize(ID),
	ProductType varchar(11) FOREIGN KEY REFERENCES ProductType(ID)
 );
 
 create table Invoice(
 -- ID: Invce0000001 -> Invce1000000
	ID varchar(12) primary key,
	CreatedDate datetime,
	EmpID varchar(6) FOREIGN KEY REFERENCES Employee(ID),
	CusID varchar(8) FOREIGN KEY REFERENCES Customer(ID),
	InvoiceNote nvarchar(200),
	Statue tinyint
 );

create table InvoiceDetail(
-- ID: InvceDetail00000001 -> InvceDetail10000000
	ID varchar(19) primary key,
	InvceID varchar(12) FOREIGN KEY REFERENCES Invoice(ID),
	ProdID varchar(10) FOREIGN KEY REFERENCES Product(ID),
	Quantity int
);


-- PROCETURES
drop proc get_Employee_login
go
create procedure get_Employee_login
	@p_Username varchar(20),
	@p_Password  varchar(30)
AS
	BEGIN
		SELECT EmpFullName, Statue, AccRole
		FROM Employee E, Account A 
		WHERE E.UserName = A.UserName and A.UserName = @p_Username and A.Passwrd = @p_Password;
	END	 
	
EXECUTE get_Employee_login 'quann','admin'

drop proc searchCustomer
go
create procedure searchCustomer
	@p_keyword varchar(100)
AS
	BEGIN
		SELECT	Customer.ID, FullName, PhoneNumber,TotalBill, typeName,
		CONVERT(INT, (SUBSTRING(Customer.ID,3,6))) AS CusInt FROM Customer, CustomerType
		WHERE Customer.CusType = CustomerType.ID and 
			( Customer.ID LIKE N'%' + @p_keyword +'%'  or
				FullName LIKE N'%' + @p_keyword +'%' or
				PhoneNumber LIKE N'%' + @p_keyword +'%' or
				typeName LIKE N'%' + @p_keyword +'%')
		ORDER BY CusInt DESC;

	END

	
execute searchCustomer 'kh'

go
create procedure insertCustomer
	@p_ID varchar(8),
	@p_fullName nvarchar(50),
	@p_phoneNumber varchar(11),
	@p_totalBill money,
	@p_cusType varchar(9)
AS
	BEGIN
		insert into Customer values(@p_ID, @p_fullName, @p_phoneNumber, @p_totalBill, @p_cusType);
	END

delete from Customer where ID = 'KH11'
exec insertCustomer 'KH11', N'Triển Chính Hy','04523568542',50000000,'CusType02'


go
create procedure updateCustomer
	@p_ID varchar(8),
	@p_fullName nvarchar(50),
	@p_phoneNumber varchar(11)
AS
	BEGIN
		update Customer set FullName = @p_fullName, PhoneNumber = @p_phoneNumber
		WHERE ID = @p_ID;
	END
exec updateCustomer 'KH1',N'Trần Gia Huynh','01234568524'
