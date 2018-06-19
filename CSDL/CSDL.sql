
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
	TypeName nvarchar(30),
	Statue tinyint
 );

 create table ProductSize(
	--ID: Size01 -> Siza001
	ID varchar(6) primary key,
	SizeName varchar(10)
 );
 create table Product(
 -- ID: Prod000001 -> Prod100000
	ID varchar(10) primary key,
	ProductName nvarchar(30),
	Price money,
	--gram
	Size varchar(6) FOREIGN KEY REFERENCES ProdSize(ID),
	ProductType varchar(11) FOREIGN KEY REFERENCES ProductType(ID),
	Statue tinyint
 );
 
 create table Invoice(
 -- ID: Invce0000001 -> Invce1000000
	ID varchar(12) primary key,
	CreatedDate datetime,
	EmpID varchar(6) FOREIGN KEY REFERENCES Employee(ID),
	CusID varchar(8) FOREIGN KEY REFERENCES Customer(ID),
	InvoiceNote nvarchar(200),
	totalBill money not null,
	statue tinyint
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
		SELECT E.ID as ID, EmpFullName, Statue, AccRole
		FROM Employee E, Account A 
		WHERE E.UserName = A.UserName and A.UserName = @p_Username and A.Passwrd = @p_Password;
	END	 
EXECUTE get_Employee_login 'quann','admin'

drop proc searchCustomer
go
create procedure searchCustomer
	@p_keyword nvarchar(100)
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

	
execute searchCustomer 'thường'

go
create procedure searchCus_ID
	@p_ID varchar(8)
AS
	BEGIN
	SELECT	Customer.ID, FullName, PhoneNumber,TotalBill, typeName,
		CONVERT(INT, (SUBSTRING(Customer.ID,3,6))) AS CusInt FROM Customer, CustomerType
		WHERE Customer.CusType = CustomerType.ID and 
		Customer.ID = @p_ID
	END
exec searchCus_ID 'KH2'
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


go
create procedure searchEmployee
	@p_keyword nvarchar(100)
AS
	BEGIN
		SELECT	Employee.ID, EmpFullName, PhoneNumber,DOB, IDCard, EmpAddress, Account.UserName, RoleAcc.RoleName, Employee.Statue,
		CONVERT(INT, (SUBSTRING(Employee.ID,3,3))) AS IDInt FROM Employee, RoleAcc, Account
		WHERE Employee.UserName = Account.UserName and
			Account.AccRole = RoleAcc.ID and 
			( Employee.ID LIKE N'%' + @p_keyword +'%'  or
				EmpFullName LIKE N'%' + @p_keyword +'%' or
				PhoneNumber LIKE N'%' + @p_keyword +'%' or
				DOB LIKE N'%' + @p_keyword +'%' or
				IDCard LIKE N'%' + @p_keyword +'%' or
				Account.UserName LIKE N'%' + @p_keyword +'%' or
				RoleAcc.RoleName LIKE N'%' + @p_keyword +'%')
		ORDER BY IDInt DESC;

	END
	drop proc searchEmployee
exec searchEmployee 'nv'

go
create procedure searchEmp_ID
	@p_ID varchar(6)
AS
	BEGIN
		SELECT	Employee.ID, EmpFullName, PhoneNumber,DOB, IDCard, EmpAddress, Account.UserName, RoleAcc.RoleName, Employee.Statue,
		CONVERT(INT, (SUBSTRING(Employee.ID,3,3))) AS IDInt FROM Employee, RoleAcc, Account
		WHERE Employee.UserName = Account.UserName and
			Account.AccRole = RoleAcc.ID and 
			Employee.ID = @p_ID
	END	

exec searchEmp_ID 'NV2'
go

go
create procedure insertAccount
	@p_Username varchar(20),
	@p_Password varchar(30),
	@p_RoleID varchar(6)
AS
	BEGIN
		insert into Account values (@p_Username, @p_Password, @p_RoleID);
	END;
EXEC insertAccount 'test','test','Role01'

go
create procedure insertEmployee
	@p_ID varchar(8),
	@p_FullName nvarchar(40),
	@p_PhoneNumber varchar(11),
	@p_DOB date,
	@p_IDCard varchar(12),
	@p_Address nvarchar(100),
	@p_UserName varchar(20),
	@p_Status tinyint
	
AS
	BEGIN
		insert into Employee values(@p_ID, @p_fullName, @p_phoneNumber, @p_DOB, @p_IDCard, @p_Address, @p_UserName, @p_Status);
	END
	delete from Employee where ID = 'NV5'
	exec insertEmployee 'NV4','test johnson','11111111111','02/18/1991','555555555','Jakarta','test',1

go 
create procedure updateAccount
	@p_username varchar(20),
	@p_password varchar(30),
	@p_accRole varchar(6)
AS
	BEGIN
		update Account set Passwrd = @p_password, AccRole = @p_accRole
		where UserName = @p_username;
	END
exec updateAccount 'test','testUpdate','Role02'
drop proc updateAccount
go
create procedure updateEmp
	@p_ID varchar(6),
	@p_name nvarchar(40),
	@p_phone varchar(11),
	@p_DOB date,
	@p_IDCard varchar(12),
	@p_address nvarchar(100),
	@p_status tinyint
AS
	BEGIN
		update Employee set EmpFullName = @p_name, PhoneNumber = @p_phone, DOB = @p_DOB, 
			IDCard = @p_IDCard, EmpAddress = @p_address, Statue = @p_status
		where ID = @p_ID;
	END
exec updateEmp 'NV4','test Update','1111111111','12/20/1989','356987542','Updated',2

go
create procedure searchProduct
	@p_keyword nvarchar(100)
AS
	BEGIN
		SELECT	Product.ID as ProdID, ProductName, Price, TypeName, SizeName, Product.Statue as ProdStatue,
		 ProductSize.ID as SizeID, ProductType.ID as TypeID, ProductType.Statue as TypeStatus, CONVERT(INT, (SUBSTRING(Product.ID,3,7))) AS IDInt 
		FROM Product, ProductType, ProductSize
		WHERE Product.ProductType = ProductType.ID and
			Product.Size = ProductSize.ID and 
			( Product.ID LIKE N'%' + @p_keyword +'%'  or
				ProductName LIKE N'%' + @p_keyword +'%' or
				Price LIKE N'%' + @p_keyword +'%' or
				TypeName LIKE N'%' + @p_keyword +'%' or
				SizeName LIKE N'%' + @p_keyword +'%')
		ORDER BY IDInt DESC;

	END
	
	exec searchProduct N'đào'
	go
create procedure searchProduct_Status
	@p_keyword nvarchar(100),
	@p_status int
AS
	BEGIN
		SELECT	Product.ID as ProdID, ProductName, Price, TypeName, SizeName, Product.Statue as ProdStatue,
		 ProductSize.ID as SizeID, ProductType.ID as TypeID, ProductType.Statue as TypeStatus, CONVERT(INT, (SUBSTRING(Product.ID,3,7))) AS IDInt 
		FROM Product, ProductType, ProductSize
		WHERE Product.ProductType = ProductType.ID and
			Product.Size = ProductSize.ID and 
				Product.Statue = @p_status	and
			( Product.ID LIKE N'%' + @p_keyword +'%'  or
				ProductName LIKE N'%' + @p_keyword +'%' or
				Price LIKE N'%' + @p_keyword +'%' or
				TypeName LIKE N'%' + @p_keyword +'%' or
				SizeName LIKE N'%' + @p_keyword +'%')
		ORDER BY IDInt DESC;

	END
	drop proc searchProduct_Status
exec searchProduct_Status N'cafe',1

go
create procedure searchProduct_TypeID
	@p_typeID varchar(11),
	@p_status tinyint
AS
	BEGIN
		SELECT	Product.ID as ProdID, ProductName, Price, TypeName, SizeName, Product.Statue as ProdStatue,
		 ProductSize.ID as SizeID, ProductType.ID as TypeID, ProductType.Statue as TypeStatus
		 From Product, ProductSize, ProductType
		 Where  Product.ProductType = ProductType.ID and
			Product.Size = ProductSize.ID and 
				Product.ProductType = @p_typeID and
				Product.Statue = @p_status
	END
	
exec searchProduct_TypeID 'ProdType1',1
go
create procedure searchProd_ID
	@p_ID varchar(10)
AS
	BEGIN
		SELECT	Product.ID as ProdID, ProductName, Price, TypeName, SizeName, Product.Statue as ProdStatue,
		 ProductSize.ID as SizeID, ProductType.ID as TypeID, ProductType.Statue as TypeStatus, CONVERT(INT, (SUBSTRING(Product.ID,3,7))) AS IDInt 
		FROM Product, ProductType, ProductSize
		WHERE Product.ProductType = ProductType.ID and
			Product.Size = ProductSize.ID and
			Product.ID = @p_ID		
	END
exec searchProd_ID 'SP10'

go
create procedure getProductType
AS
	BEGIN
		select ID, TypeName, Statue from ProductType
	END;
	exec getProductType
create procedure getProductSize
AS
	BEGIN
		select ID, ProductSize.SizeName as SizeName from ProductSize
	END

exec getProductSize

create procedure insertProduct
	@p_ID varchar(10),
	@p_Name nvarchar(30),
	@p_Price money,
	@p_Size varchar(6),
	@p_Type varchar(11),
	@p_Statue tinyint
AS
	BEGIN
		insert into Product values (@p_ID, @p_Name, @p_Price, @p_Size, @p_Type, @p_Statue);
	END
exec insertProduct 'SP41',N'Thạch táo',5000,'Size0','ProdType6',1

create procedure updateProduct
	@p_ID varchar(10),
	@p_Name nvarchar(30),
	@p_Price money,
	@p_Size varchar(6),
	@p_Type varchar(11),
	@p_Statue tinyint
AS
	BEGIN
		update Product set ProductName = @p_Name, Price = @p_Price, Size = @p_Size, ProductType = @p_Type, Statue = @p_Statue
		where ID = @p_ID
	END
exec updateProduct 'SP1','test update',15000,'Size2','ProdType2',2

go
create procedure searchInvoice
	@p_fromDate date,
	@p_toDate date
AS
	BEGIN
		select Invoice.ID, CreatedDate, UserName, Customer.FullName as CusName, InvoiceNote, Invoice.totalBill as totalBill,
			Invoice.statue as statue, CONVERT(INT, (SUBSTRING(Invoice.ID,3,9))) AS IDInt 
		FROM Invoice, Employee, Customer
		WHERE Invoice.EmpID = Employee.ID and Invoice.CusID = Customer.ID
		and (CreatedDate between @p_fromDate and @p_toDate)
		ORDER BY IDInt DESC;
	END
	
exec searchInvoice '01/01/2017','06/08/2018'

go
create procedure getProd
	@p_prodName nvarchar(30),
	@p_sizeID varchar(6)
AS
	BEGIN
		select ID, ProductName, Price, Size, ProductType, Statue from Product
		where ProductName = @p_prodName and Size = @p_sizeID;
	END
	drop proc getProd
exec getProd N'Trà sữa kiwi','Size2'


go
create procedure insertInvoiceDetail
	@p_ID varchar(19),
	@p_InvoiceID varchar(12),
	@p_ProdID varchar(10),
	@p_Quantity int
AS
	BEGIN
		insert InvoiceDetail values (@p_ID, @p_InvoiceID, @p_ProdID, @p_Quantity);
	END

exec insertInvoiceDetail 'CTHD2','HD1','SP2',1

go
create procedure insertInvoice
	@p_ID varchar(12),
	@p_EmpID varchar(6),
	@p_CusID varchar(8),
	@p_totalBill money
AS
	BEGIN
		insert into Invoice values(@p_ID, CURRENT_TIMESTAMP, @p_EmpID, @p_CusID, null,@p_totalBill,1);
	END

exec insertInvoice 'HD27','NV3','KH6',24000

go
create procedure searchInvoiceDetail
	@p_keyword varchar(19)
AS
	BEGIN
		select ID, InvceID, ProdID, Quantity,
			CONVERT(INT, (SUBSTRING(ID,5,14))) AS IDInt 
		FROM InvoiceDetail
		WHERE ( InvceID LIKE N'%' + @p_keyword +'%'  or
				ProdID LIKE N'%' + @p_keyword +'%')
		ORDER BY IDInt DESC;
	END

	exec searchInvoiceDetail 'HD29'

go
create procedure searchInvoiceID
	@p_keyword nvarchar(100)
AS
	BEGIN
		SELECT	ID, CreatedDate, EmpID, CusID, InvoiceNote, totalBill, statue, 
		CONVERT(INT, (SUBSTRING(ID,3,9))) AS IDInt 
		FROM Invoice
		WHERE  
			( ID LIKE N'%' + @p_keyword +'%')
		ORDER BY IDInt DESC;

	END
exec searchInvoiceID ''
	