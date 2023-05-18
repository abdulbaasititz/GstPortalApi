package com.itz.gst.use_cases.gst_module.portal.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class GenerateIrnDao {
    @JsonProperty("Version")
    public String version;
    @JsonProperty("TranDtls")
    public TranDtls tranDtls;
    @JsonProperty("DocDtls")
    public DocDtls docDtls;
    @JsonProperty("SellerDtls")
    public SellerDtls sellerDtls;
    @JsonProperty("BuyerDtls")
    public BuyerDtls buyerDtls;
    @JsonProperty("DispDtls")
    public DispDtls dispDtls;
    @JsonProperty("ShipDtls")
    public ShipDtls shipDtls;
    @JsonProperty("ItemList")
    public ArrayList<ItemList> itemList;
    @JsonProperty("ValDtls")
    public ValDtls valDtls;
    @JsonProperty("PayDtls")
    public PayDtls payDtls;
    @JsonProperty("RefDtls")
    public RefDtls refDtls;
    @JsonProperty("AddlDocDtls")
    public ArrayList<AddlDocDtl> addlDocDtls;
    @JsonProperty("ExpDtls")
    public ExpDtls expDtls;
    @JsonProperty("EwbDtls")
    public EwbDtls ewbDtls;

}

@Setter @Getter
class TranDtls{
    @JsonProperty("TaxSch")
    public String taxSch;
    @JsonProperty("SupTyp")
    public String supTyp;
    @JsonProperty("RegRev")
    public String regRev;
    @JsonProperty("EcmGstin")
    public Object ecmGstin;
    @JsonProperty("IgstOnIntra")
    public String igstOnIntra;
}

@Getter @Setter
class DocDtls{
    @JsonProperty("Typ")
    public String typ;
    @JsonProperty("No")
    public String no;
    @JsonProperty("Dt")
    public String dt;
}

@Getter @Setter
class SellerDtls{
    @JsonProperty("Gstin")
    public String gstin;
    @JsonProperty("LglNm")
    public String lglNm;
    @JsonProperty("TrdNm")
    public String trdNm;
    @JsonProperty("Addr1")
    public String addr1;
    @JsonProperty("Addr2")
    public String addr2;
    @JsonProperty("Loc")
    public String loc;
    @JsonProperty("Pin")
    public int pin;
    @JsonProperty("Stcd")
    public String stcd;
    @JsonProperty("Ph")
    public String ph;
    @JsonProperty("Em")
    public String em;
}
@Getter @Setter
class ValDtls{
    @JsonProperty("AssVal")
    public double assVal;
    @JsonProperty("CgstVal")
    public int cgstVal;
    @JsonProperty("SgstVal")
    public int sgstVal;
    @JsonProperty("IgstVal")
    public double igstVal;
    @JsonProperty("CesVal")
    public double cesVal;
    @JsonProperty("StCesVal")
    public double stCesVal;
    @JsonProperty("Discount")
    public int discount;
    @JsonProperty("OthChrg")
    public int othChrg;
    @JsonProperty("RndOffAmt")
    public double rndOffAmt;
    @JsonProperty("TotInvVal")
    public int totInvVal;
    @JsonProperty("TotInvValFc")
    public double totInvValFc;
}

@Getter @Setter
class BuyerDtls{
    @JsonProperty("Gstin")
    public String gstin;
    @JsonProperty("LglNm")
    public String lglNm;
    @JsonProperty("TrdNm")
    public String trdNm;
    @JsonProperty("Pos")
    public String pos;
    @JsonProperty("Addr1")
    public String addr1;
    @JsonProperty("Addr2")
    public String addr2;
    @JsonProperty("Loc")
    public String loc;
    @JsonProperty("Pin")
    public int pin;
    @JsonProperty("Stcd")
    public String stcd;
    @JsonProperty("Ph")
    public String ph;
    @JsonProperty("Em")
    public String em;
}

@Getter @Setter
class DispDtls{
    @JsonProperty("Nm")
    public String nm;
    @JsonProperty("Addr1")
    public String addr1;
    @JsonProperty("Addr2")
    public String addr2;
    @JsonProperty("Loc")
    public String loc;
    @JsonProperty("Pin")
    public int pin;
    @JsonProperty("Stcd")
    public String stcd;
}
@Getter @Setter
class ItemList{
    @JsonProperty("SlNo")
    public String slNo;
    @JsonProperty("PrdDesc")
    public String prdDesc;
    @JsonProperty("IsServc")
    public String isServc;
    @JsonProperty("HsnCd")
    public String hsnCd;
    @JsonProperty("Barcde")
    public String barcde;
    @JsonProperty("Qty")
    public double qty;
    @JsonProperty("FreeQty")
    public int freeQty;
    @JsonProperty("Unit")
    public String unit;
    @JsonProperty("UnitPrice")
    public double unitPrice;
    @JsonProperty("TotAmt")
    public double totAmt;
    @JsonProperty("Discount")
    public int discount;
    @JsonProperty("PreTaxVal")
    public int preTaxVal;
    @JsonProperty("AssAmt")
    public double assAmt;
    @JsonProperty("GstRt")
    public double gstRt;
    @JsonProperty("IgstAmt")
    public double igstAmt;
    @JsonProperty("CgstAmt")
    public int cgstAmt;
    @JsonProperty("SgstAmt")
    public int sgstAmt;
    @JsonProperty("CesRt")
    public int cesRt;
    @JsonProperty("CesAmt")
    public double cesAmt;
    @JsonProperty("CesNonAdvlAmt")
    public int cesNonAdvlAmt;
    @JsonProperty("StateCesRt")
    public int stateCesRt;
    @JsonProperty("StateCesAmt")
    public double stateCesAmt;
    @JsonProperty("StateCesNonAdvlAmt")
    public int stateCesNonAdvlAmt;
    @JsonProperty("OthChrg")
    public int othChrg;
    @JsonProperty("TotItemVal")
    public double totItemVal;
    @JsonProperty("OrdLineRef")
    public String ordLineRef;
    @JsonProperty("OrgCntry")
    public String orgCntry;
    @JsonProperty("PrdSlNo")
    public String prdSlNo;
    @JsonProperty("BchDtls")
    public BchDtls bchDtls;
    @JsonProperty("AttribDtls")
    public ArrayList<AttribDtl> attribDtls;
}

@Getter @Setter
class PayDtls{
    @JsonProperty("Nm")
    public String nm;
    @JsonProperty("AccDet")
    public String accDet;
    @JsonProperty("Mode")
    public String mode;
    @JsonProperty("FinInsBr")
    public String finInsBr;
    @JsonProperty("PayTerm")
    public String payTerm;
    @JsonProperty("PayInstr")
    public String payInstr;
    @JsonProperty("CrTrn")
    public String crTrn;
    @JsonProperty("DirDr")
    public String dirDr;
    @JsonProperty("CrDay")
    public int crDay;
    @JsonProperty("PaidAmt")
    public int paidAmt;
    @JsonProperty("PaymtDue")
    public int paymtDue;
}
@Getter @Setter
class PrecDocDtl{
    @JsonProperty("InvNo")
    public String invNo;
    @JsonProperty("InvDt")
    public String invDt;
    @JsonProperty("OthRefNo")
    public String othRefNo;
}
@Getter @Setter
class RefDtls{
    @JsonProperty("InvRm")
    public String invRm;
    @JsonProperty("DocPerdDtls")
    public DocPerdDtls docPerdDtls;
    @JsonProperty("PrecDocDtls")
    public ArrayList<PrecDocDtl> precDocDtls;
    @JsonProperty("ContrDtls")
    public ArrayList<ContrDtl> contrDtls;
}
@Getter @Setter
class AddlDocDtl{
    @JsonProperty("Url")
    public String url;
    @JsonProperty("Docs")
    public String docs;
    @JsonProperty("Info")
    public String info;
}
@Getter @Setter
class AttribDtl{
    @JsonProperty("Nm")
    public String nm;
    @JsonProperty("Val")
    public String val;
}
@Getter @Setter
class BchDtls{
    @JsonProperty("Nm")
    public String nm;
    @JsonProperty("ExpDt")
    public String expDt;
    @JsonProperty("WrDt")
    public String wrDt;
}
@Getter @Setter
class ContrDtl{
    @JsonProperty("RecAdvRefr")
    public String recAdvRefr;
    @JsonProperty("RecAdvDt")
    public String recAdvDt;
    @JsonProperty("TendRefr")
    public String tendRefr;
    @JsonProperty("ContrRefr")
    public String contrRefr;
    @JsonProperty("ExtRefr")
    public String extRefr;
    @JsonProperty("ProjRefr")
    public String projRefr;
    @JsonProperty("PORefr")
    public String pORefr;
    @JsonProperty("PORefDt")
    public String pORefDt;
}
@Getter @Setter
class DocPerdDtls{
    @JsonProperty("InvStDt")
    public String invStDt;
    @JsonProperty("InvEndDt")
    public String invEndDt;
}
@Getter @Setter
class EwbDtls{
    @JsonProperty("TransId")
    public String transId;
    @JsonProperty("TransName")
    public String transName;
    @JsonProperty("Distance")
    public int distance;
    @JsonProperty("TransDocNo")
    public String transDocNo;
    @JsonProperty("TransDocDt")
    public String transDocDt;
    @JsonProperty("VehNo")
    public String vehNo;
    @JsonProperty("VehType")
    public String vehType;
    @JsonProperty("TransMode")
    public String transMode;
}
@Getter @Setter
class ExpDtls{
    @JsonProperty("ShipBNo")
    public String shipBNo;
    @JsonProperty("ShipBDt")
    public String shipBDt;
    @JsonProperty("Port")
    public String port;
    @JsonProperty("RefClm")
    public String refClm;
    @JsonProperty("ForCur")
    public String forCur;
    @JsonProperty("CntCode")
    public String cntCode;
    @JsonProperty("ExpDuty")
    public Object expDuty;
}
@Getter @Setter
class ShipDtls{
    @JsonProperty("Gstin")
    public String gstin;
    @JsonProperty("LglNm")
    public String lglNm;
    @JsonProperty("TrdNm")
    public String trdNm;
    @JsonProperty("Addr1")
    public String addr1;
    @JsonProperty("Addr2")
    public String addr2;
    @JsonProperty("Loc")
    public String loc;
    @JsonProperty("Pin")
    public int pin;
    @JsonProperty("Stcd")
    public String stcd;
}