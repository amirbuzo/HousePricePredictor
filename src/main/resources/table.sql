USE [ALCIRT_DB]
GO

ALTER TABLE [dbo].[appart] DROP CONSTRAINT [DF_appart_legalizuar]
GO

ALTER TABLE [dbo].[appart] DROP CONSTRAINT [DF_appart_meashensor]
GO

ALTER TABLE [dbo].[appart] DROP CONSTRAINT [DF_appart_mobiluar]
GO

ALTER TABLE [dbo].[appart] DROP CONSTRAINT [DF_appart_estate]
GO

/****** Object:  Table [dbo].[appart]    Script Date: 2/25/2021 2:43:31 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[appart]') AND type in (N'U'))
DROP TABLE [dbo].[appart]
GO

/****** Object:  Table [dbo].[appart]    Script Date: 2/25/2021 2:43:31 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[appart](
	[viti] [bigint] NULL,
	[tipi] [varchar](50) NULL,
	[siperfaqe] [bigint] NULL,
	[cmimi] [bigint] NULL,
	[valuta] [varchar](50) NULL,
	[kati] [bigint] NULL,
	[forma] [varchar](50) NULL,
	[zona] [varchar](50) NULL,
	[estate] [varchar](10) NULL,
	[mobiluar] [varchar](50) NULL,
	[meashensor] [varchar](50) NULL,
	[legalizuar] [varchar](50) NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[appart] ADD  CONSTRAINT [DF_appart_estate]  DEFAULT ('estate') FOR [estate]
GO

ALTER TABLE [dbo].[appart] ADD  CONSTRAINT [DF_appart_mobiluar]  DEFAULT ('no') FOR [mobiluar]
GO

ALTER TABLE [dbo].[appart] ADD  CONSTRAINT [DF_appart_meashensor]  DEFAULT (N'yes') FOR [meashensor]
GO



/****** Script for SelectTopNRows command from SSMS  ******/
SELECT  forma, count(*)
  FROM [ALCIRT_PORTAL].[dbo].[appart]
  group by forma order by forma

/****** 
  Delete   from appart where
  zona = null or zona ='' or zona ='11 Rinas'
  or zona ='11 Vore'
  or zona ='11 Autostrada Tr.-Dr.'
  or zona ='11 QTU / Kashar / Casa Italia'
  or zona ='2 Autostrada Tr.-El.'
  or forma =null 
  or forma = '4+1'
  or forma ='3+1'
  or tipi not like 'apartament'

   Update appart 
 set cmimi = cmimi * siperfaqe where valuta like 'Euro/m2'

  Delete   from appart where
  cmimi <5000 or cmimi >500000
  or cmimi is null

     Update appart 
 set forma = '1+1' where forma like '1'
 ******/
 
     Update appart 
 set forma = 'one_plus_one' where forma like '' and siperfaqe <80

  Update appart 
 set zona = REPLACE(zona, '-', '')

   
  Delete   from appart where
 
  forma ='' 


  /****** Script for SelectTopNRows command from SSMS  ******/
SELECT   [siperfaqe]
  ,[mobiluar]
     ,[meashensor]
	    ,[estate]
		 ,[legalizuar]
		
		,[viti]
		 ,[zona]
 	  ,[forma]
      ,[cmimi]

      
      
   
    
   
     
  FROM [ALCIRT_PORTAL].[dbo].[appart]

ALTER TABLE [dbo].[appart] ADD  CONSTRAINT [DF_appart_legalizuar]  DEFAULT ('po') FOR [legalizuar]
GO


