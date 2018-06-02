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

 create table Volume(
	--ID: Vol0000001 -> Vol1000000
	ID varchar(10) primary key,
	VolValue int,
	UsedTimes int
 );
 create table Product(
 -- ID: Prod000001 -> Prod100000
	ID varchar(10) primary key,
	ProductName nvarchar(30),
	Price money,
	Price_text nvarchar(100),
	ValidDate date,
	Quantity int,
	--gram
	Volume varchar(10) FOREIGN KEY REFERENCES Volume(ID),
	UsedTimes int,
	ProductType varchar(11) FOREIGN KEY REFERENCES ProductType(ID)
 );
 
 create table Invoice(
 -- ID: Invce0000001 -> Invce1000000
	ID varchar(12) primary key,
	CreatedDate date,
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


-- TRIGGERS

GO
-- Firing when a row of InvoiceDetail is inserted
-- Checking Product Quantity
CREATE TRIGGER ProductQuantity ON InvoiceDetail  
AFTER INSERT  
AS  
	-- inserted value
	DECLARE @v_InvoiceDetailID varchar(19);
	DECLARE @v_InvceID varchar(12);
	DECLARE @v_InsertedQuantity int;
	DECLARE @v_ProductID varchar(10);
	----------------------------------
	DECLARE @v_UsedTimes int;
	DECLARE @v_VolumeID varchar(10);
	DECLARE @v_VolumeUsedTimes int;
	DECLARE @v_ProdQuantity int;

	-- inserted value
	SELECT @v_ProductID = i.ProdID FROM inserted i;
	SELECT @v_InvoiceDetailID = i.ID FROM inserted i;
	SELECT @v_InvceID = i.InvceID FROM inserted i;
	SELECT @v_InsertedQuantity = i.Quantity FROM inserted i;
	-----------------------------------------------------------
	SELECT @v_ProdQuantity = p.quantity FROM Product p WHERE p.ID = @v_ProductID;
	SELECT @v_UsedTimes = (P.UsedTimes + @v_InsertedQuantity) FROM Product P WHERE P.ID = @v_ProductID;
	SELECT @v_VolumeID = P.volume FROM Product P WHERE P.ID = @v_ProductID;
	SELECT @v_VolumeUsedTimes = UsedTimes FROM Volume WHERE Volume.ID = @v_VolumeID;

	

	if(@v_UsedTimes > @v_VolumeUsedTimes)
		BEGIN  
			IF(@v_ProdQuantity > 0)
				BEGIN
					UPDATE Product SET quantity = quantity - 1 WHERE Product.ID = @v_ProductID;
					UPDATE Product SET UsedTimes = @v_UsedTimes - @v_VolumeUsedTimes
									 WHERE Product.ID = @v_ProductID;
					--- insert invoice detail
					INSERT INTO InvoiceDetail VALUES(@v_InvoiceDetailID,@v_InvceID,@v_ProductID,@v_InsertedQuantity);
				END
			ELSE
			BEGIN 
				PRINT ('Product is sold out!!');  
				ROLLBACK TRANSACTION;  
				RETURN 
			END
		END
	else
		BEGIN
		 UPDATE Product set UsedTimes = UsedTimes + @v_InsertedQuantity WHERE Product.ID = @v_ProductID;
		 INSERT INTO InvoiceDetail VALUES(@v_InvoiceDetailID,@v_InvceID,@v_ProductID,@v_InsertedQuantity);
		END;



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
