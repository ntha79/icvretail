alter table ICVRETAIL_PURCHASES drop column DATE_ cascade ;
alter table ICVRETAIL_PURCHASES add column DATE_ timestamp ;
