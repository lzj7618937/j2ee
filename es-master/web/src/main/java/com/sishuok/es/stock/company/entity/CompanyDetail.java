package com.sishuok.es.stock.company.entity;

import com.sishuok.es.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * Created by jaylee on 15-4-30.
 */
@Entity
@Table(name = "stock_company_detail")
public class CompanyDetail extends BaseEntity<Long> {


    @Column(name = "stock_code")
    private String stockCode;
    @Column(name = "ssrq")
    private String ssrq;
    @Column(name = "zyyw")
    private String zyyw;
    @Column(name = "ywxx")
    private String ywxx;
    @Column(name = "zgb")
    private String zgb;
    @Column(name = "ltg")
    private String ltg;
    @Column(name = "yysrzzl")
    private String yysrzzl;
    @Column(name = "yysr")
    private String yysr;
    @Column(name = "jlr")
    private String jlr;
    @Column(name = "jlrzzl")
    private String jlrzzl;
    @Column(name = "mgsy")
    private String mgsy;
    @Column(name = "mgjzc")
    private String mgjzc;
    @Column(name = "jzcsyl")
    private String jzcsyl;
    @Column(name = "mgxjl")
    private String mgxjl;
    @Column(name = "mggjj")
    private String mggjj;
    @Column(name = "mgwfplr")
    private String mgwfplr;
    @Column(name = "gsmc")
    private String gsmc;
    @Column(name = "ssdy")
    private String ssdy;
    @Column(name = "sjgn")
    private String sjgn;
    @Column(name = "sshy")
    private String sshy;
    @Column(name = "data_date")
    private String dataDate;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getSsrq() {
        return ssrq;
    }

    public void setSsrq(String ssrq) {
        this.ssrq = ssrq;
    }

    public String getZyyw() {
        return zyyw;
    }

    public void setZyyw(String zyyw) {
        this.zyyw = zyyw;
    }

    public String getYwxx() {
        return ywxx;
    }

    public void setYwxx(String ywxx) {
        this.ywxx = ywxx;
    }

    public String getZgb() {
        return zgb;
    }

    public void setZgb(String zgb) {
        this.zgb = zgb;
    }

    public String getLtg() {
        return ltg;
    }

    public void setLtg(String ltg) {
        this.ltg = ltg;
    }

    public String getYysrzzl() {
        return yysrzzl;
    }

    public void setYysrzzl(String yysrzzl) {
        this.yysrzzl = yysrzzl;
    }

    public String getYysr() {
        return yysr;
    }

    public void setYysr(String yysr) {
        this.yysr = yysr;
    }

    public String getJlr() {
        return jlr;
    }

    public void setJlr(String jlr) {
        this.jlr = jlr;
    }

    public String getJlrzzl() {
        return jlrzzl;
    }

    public void setJlrzzl(String jlrzzl) {
        this.jlrzzl = jlrzzl;
    }

    public String getMgsy() {
        return mgsy;
    }

    public void setMgsy(String mgsy) {
        this.mgsy = mgsy;
    }

    public String getMgjzc() {
        return mgjzc;
    }

    public void setMgjzc(String mgjzc) {
        this.mgjzc = mgjzc;
    }

    public String getJzcsyl() {
        return jzcsyl;
    }

    public void setJzcsyl(String jzcsyl) {
        this.jzcsyl = jzcsyl;
    }

    public String getMgxjl() {
        return mgxjl;
    }

    public void setMgxjl(String mgxjl) {
        this.mgxjl = mgxjl;
    }

    public String getMggjj() {
        return mggjj;
    }

    public void setMggjj(String mggjj) {
        this.mggjj = mggjj;
    }

    public String getMgwfplr() {
        return mgwfplr;
    }

    public void setMgwfplr(String mgwfplr) {
        this.mgwfplr = mgwfplr;
    }

    public String getGsmc() {
        return gsmc;
    }

    public void setGsmc(String gsmc) {
        this.gsmc = gsmc;
    }

    public String getSsdy() {
        return ssdy;
    }

    public void setSsdy(String ssdy) {
        this.ssdy = ssdy;
    }

    public String getSjgn() {
        return sjgn;
    }

    public void setSjgn(String sjgn) {
        this.sjgn = sjgn;
    }

    public String getSshy() {
        return sshy;
    }

    public void setSshy(String sshy) {
        this.sshy = sshy;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyDetail that = (CompanyDetail) o;

        if (stockCode != null ? !stockCode.equals(that.stockCode) : that.stockCode != null) return false;
        if (ssrq != null ? !ssrq.equals(that.ssrq) : that.ssrq != null) return false;
        if (zyyw != null ? !zyyw.equals(that.zyyw) : that.zyyw != null) return false;
        if (ywxx != null ? !ywxx.equals(that.ywxx) : that.ywxx != null) return false;
        if (zgb != null ? !zgb.equals(that.zgb) : that.zgb != null) return false;
        if (ltg != null ? !ltg.equals(that.ltg) : that.ltg != null) return false;
        if (yysrzzl != null ? !yysrzzl.equals(that.yysrzzl) : that.yysrzzl != null) return false;
        if (yysr != null ? !yysr.equals(that.yysr) : that.yysr != null) return false;
        if (jlr != null ? !jlr.equals(that.jlr) : that.jlr != null) return false;
        if (jlrzzl != null ? !jlrzzl.equals(that.jlrzzl) : that.jlrzzl != null) return false;
        if (mgsy != null ? !mgsy.equals(that.mgsy) : that.mgsy != null) return false;
        if (mgjzc != null ? !mgjzc.equals(that.mgjzc) : that.mgjzc != null) return false;
        if (jzcsyl != null ? !jzcsyl.equals(that.jzcsyl) : that.jzcsyl != null) return false;
        if (mgxjl != null ? !mgxjl.equals(that.mgxjl) : that.mgxjl != null) return false;
        if (mggjj != null ? !mggjj.equals(that.mggjj) : that.mggjj != null) return false;
        if (mgwfplr != null ? !mgwfplr.equals(that.mgwfplr) : that.mgwfplr != null) return false;
        if (gsmc != null ? !gsmc.equals(that.gsmc) : that.gsmc != null) return false;
        if (ssdy != null ? !ssdy.equals(that.ssdy) : that.ssdy != null) return false;
        if (sjgn != null ? !sjgn.equals(that.sjgn) : that.sjgn != null) return false;
        if (sshy != null ? !sshy.equals(that.sshy) : that.sshy != null) return false;
        if (dataDate != null ? !dataDate.equals(that.dataDate) : that.dataDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stockCode != null ? stockCode.hashCode() : 0;
        result = 31 * result + (ssrq != null ? ssrq.hashCode() : 0);
        result = 31 * result + (zyyw != null ? zyyw.hashCode() : 0);
        result = 31 * result + (ywxx != null ? ywxx.hashCode() : 0);
        result = 31 * result + (zgb != null ? zgb.hashCode() : 0);
        result = 31 * result + (ltg != null ? ltg.hashCode() : 0);
        result = 31 * result + (yysrzzl != null ? yysrzzl.hashCode() : 0);
        result = 31 * result + (yysr != null ? yysr.hashCode() : 0);
        result = 31 * result + (jlr != null ? jlr.hashCode() : 0);
        result = 31 * result + (jlrzzl != null ? jlrzzl.hashCode() : 0);
        result = 31 * result + (mgsy != null ? mgsy.hashCode() : 0);
        result = 31 * result + (mgjzc != null ? mgjzc.hashCode() : 0);
        result = 31 * result + (jzcsyl != null ? jzcsyl.hashCode() : 0);
        result = 31 * result + (mgxjl != null ? mgxjl.hashCode() : 0);
        result = 31 * result + (mggjj != null ? mggjj.hashCode() : 0);
        result = 31 * result + (mgwfplr != null ? mgwfplr.hashCode() : 0);
        result = 31 * result + (gsmc != null ? gsmc.hashCode() : 0);
        result = 31 * result + (ssdy != null ? ssdy.hashCode() : 0);
        result = 31 * result + (sjgn != null ? sjgn.hashCode() : 0);
        result = 31 * result + (sshy != null ? sshy.hashCode() : 0);
        result = 31 * result + (dataDate != null ? dataDate.hashCode() : 0);
        return result;
    }

}
