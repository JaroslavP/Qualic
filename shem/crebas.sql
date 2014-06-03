/*==============================================================*/
/* Table: bms_bridge                                            */
/*==============================================================*/
create table bms_bridge (
   bridge_id            SERIAL                not null,
   bridge_name          CHAR(255)         	  null,
   bridge_barrier       CHAR(255)             null,
   bridge_category      INTEGER               null,
   bridge_lines         INTEGER               null,
   bridge_locality      CHAR(255)             null,
   bridge_distanceToLocality REAL             null,
   bridge_input         DATE                  null,
   bridge_company       CHAR(255)             null,
   bridge_repair        DATE                  null,
   bridge_repairCompany CHAR(255)             null,
   bridge_lastInspection DATE                 null,
   bridge_lifetime      DATE                  null,
   constraint PK_BMS_BRIDGE primary key (bridge_id)
);

/*==============================================================*/
/* Index: bms_bridge_PK                                         */
/*==============================================================*/
create unique index bms_bridge_PK on bms_bridge (
bridge_id
);

/*==============================================================*/
/* Table: SMETA                                                 */
/*==============================================================*/
create table SMETA (
   smeta_id             SERIAL                 not null,
   bridge_id            INTEGER                not null REFERENCES bms_bridge ON DELETE CASCADE,
   smeta_name           REAL               null,
   smeta_unit           REAL               null,
   smeta_numder         REAL               null,
   smeta_numberCost     REAL               null,
   smeta_sumNumCost     REAL               null,
   smeta_unitWork       REAL               null,
   smeta_sumUnitWork    REAL               null,
   smeta_total          REAL               null,
   constraint PK_SMETA primary key (smeta_id)
);

/*==============================================================*/
/* Index: SMETA_PK                                              */
/*==============================================================*/
create unique index SMETA_PK on SMETA (
smeta_id
);

/*==============================================================*/
/* Index: bridge_smeta_FK                                       */
/*==============================================================*/
create  index bridge_smeta_FK on SMETA (
bridge_id
);

/*==============================================================*/
/* Table: bms_specification                                     */
/*==============================================================*/
create table bms_specification (
   specific_id          SERIAL                 not null,
   bridge_id            INTEGER                 not null REFERENCES bms_bridge ON DELETE CASCADE,
   specific_length      INTEGER                 null,
   specific_raodWidth   INTEGER                 null,
   specific_leftSidewalk INTEGER                 null,
   specific_rightSidewalk INTEGER                 null,
   specific_fence       INTEGER                 null,
   specific_angle       REAL               null,
   specific_load        REAL               null,
   constraint PK_BMS_SPECIFICATION primary key (specific_id)
);

/*==============================================================*/
/* Index: bms_specification_PK                                  */
/*==============================================================*/
create unique index bms_specification_PK on bms_specification (
specific_id
);

/*==============================================================*/
/* Index: bridge_specificatetion_FK                             */
/*==============================================================*/
create  index bridge_specificatetion_FK on bms_specification (
bridge_id
);

/*==============================================================*/
/* Table: bms_rating                                            */
/*==============================================================*/
create table bms_rating (
   rating_id            SERIAL                not null,
   bridge_id            INTEGER                 not null REFERENCES bms_bridge ON DELETE CASCADE,
   rating_CategoryStatus REAL               null,
   rating_Deck          REAL               null,
   rating_Superstructure REAL               null,
   rating_Support       REAL               null,
   rating_RegulatoryStructure REAL               null,
   constraint PK_BMS_RATING primary key (rating_id)
);

/*==============================================================*/
/* Index: bms_rating_PK                                         */
/*==============================================================*/
create unique index bms_rating_PK on bms_rating (
rating_id
);

/*==============================================================*/
/* Index: bridge_rating_FK                                      */
/*==============================================================*/
create  index bridge_rating_FK on bms_rating (
bridge_id
);

/*==============================================================*/
/* Table: bms_deck                                              */
/*==============================================================*/
create table bms_deck (
   deck_id              SERIAL                not null,
   rating_id            INTEGER                 not null REFERENCES bms_rating ON DELETE CASCADE,
   deck_coating         INTEGER                 null,
   deck_drainage        INTEGER                 null,
   deck_communication   INTEGER                 null,
   deck_expensionJoins  INTEGER                 null,
   deck_fence           INTEGER                 null,
   deck_waterproofing   INTEGER                 null,
   deck_sidewalk        INTEGER                 null,
   deck_barrier         INTEGER                 null,
   constraint PK_BMS_DECK primary key (deck_id)
);

/*==============================================================*/
/* Index: bms_deck_PK                                           */
/*==============================================================*/
create unique index bms_deck_PK on bms_deck (
deck_id
);

/*==============================================================*/
/* Index: rating_deck_FK                                        */
/*==============================================================*/
create  index rating_deck_FK on bms_deck (
rating_id
);

/*==============================================================*/
/* Table: bms_regulatory_structure                              */
/*==============================================================*/
create table bms_regulatory_structure (
   rs_id                SERIAL                not null,
   rating_id            INTEGER                 not null REFERENCES bms_rating ON DELETE CASCADE,
   rs_cone              INTEGER                 null,
   rs_strengthening     INTEGER                 null,
   constraint PK_BMS_REGULATORY_STRUCTURE primary key (rs_id)
);

/*==============================================================*/
/* Index: bms_regulatory_structure_PK                           */
/*==============================================================*/
create unique index bms_regulatory_structure_PK on bms_regulatory_structure (
rs_id
);

/*==============================================================*/
/* Index: rating_regulatory_structure_FK                        */
/*==============================================================*/
create  index rating_regulatory_structure_FK on bms_regulatory_structure (
rating_id
);

/*==============================================================*/
/* Table: bms_superstructure                                    */
/*==============================================================*/
create table bms_superstructure (
   ss_id                SERIAL                 not null,
   rating_id            INTEGER                 not null REFERENCES bms_rating ON DELETE CASCADE,
   ss_reinforrcingBars  INTEGER                 null,
   ss_suppotStructure   INTEGER                 null,
   ss_ties              INTEGER                 null,
   ss_bsicport          INTEGER                 null,
   constraint PK_BMS_SUPERSTRUCTURE primary key (ss_id)
);

/*==============================================================*/
/* Index: bms_superstructure_PK                                 */
/*==============================================================*/
create unique index bms_superstructure_PK on bms_superstructure (
ss_id
);

/*==============================================================*/
/* Index: rating_superstructure_FK                              */
/*==============================================================*/
create  index rating_superstructure_FK on bms_superstructure (
rating_id
);

/*==============================================================*/
/* Table: bms_support                                           */
/*==============================================================*/
create table bms_support (
   support_id           SERIAL                not null,
   rating_id            INTEGER                 not null REFERENCES bms_rating ON DELETE CASCADE,
   support_header       INTEGER                 null,
   support_bodySupport  INTEGER                 null,
   support_foundation   INTEGER                 null,
   constraint PK_BMS_SUPPORT primary key (support_id)
);

/*==============================================================*/
/* Index: bms_support_PK                                        */
/*==============================================================*/
create unique index bms_support_PK on bms_support (
support_id
);

/*==============================================================*/
/* Index: rating_support_FK                                     */
/*==============================================================*/
create  index rating_support_FK on bms_support (
rating_id
);

alter table SMETA
   add constraint FK_SMETA_BRIDGE_SM_BMS_BRID foreign key (bridge_id)
      references bms_bridge (bridge_id)
      on delete restrict on update restrict;

alter table bms_deck
   add constraint FK_BMS_DECK_RATING_DE_BMS_RATI foreign key (rating_id)
      references bms_rating (rating_id)
      on delete restrict on update restrict;

alter table bms_rating
   add constraint FK_BMS_RATI_BRIDGE_RA_BMS_BRID foreign key (bridge_id)
      references bms_bridge (bridge_id)
      on delete restrict on update restrict;

alter table bms_regulatory_structure
   add constraint FK_BMS_REGU_RATING_RE_BMS_RATI foreign key (rating_id)
      references bms_rating (rating_id)
      on delete restrict on update restrict;

alter table bms_specification
   add constraint FK_BMS_SPEC_BRIDGE_SP_BMS_BRID foreign key (bridge_id)
      references bms_bridge (bridge_id)
      on delete restrict on update restrict;

alter table bms_superstructure
   add constraint FK_BMS_SUPE_RATING_SU_BMS_RATI foreign key (rating_id)
      references bms_rating (rating_id)
      on delete restrict on update restrict;

alter table bms_support
   add constraint FK_BMS_SUPP_RATING_SU_BMS_RATI foreign key (rating_id)
      references bms_rating (rating_id)
      on delete restrict on update restrict;

