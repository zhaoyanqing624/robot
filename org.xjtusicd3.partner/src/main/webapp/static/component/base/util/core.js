/**
 * ���ò�����
 */

//��ȡΨһֵ
function GUID() {
  this.date = new Date();
 
  /* �ж��Ƿ��ʼ�����������ʼ�������´��룬�����´��뽫����ִ�У�ʵ����ִֻ��һ�� */
  if (typeof this.newGUID != 'function') {
     
    /* ����GUID�� */
    GUID.prototype.newGUID = function() {
      this.date = new Date();
      var guidStr = '';
        sexadecimalDate = this.hexadecimal(this.getGUIDDate(), 16);
        sexadecimalTime = this.hexadecimal(this.getGUIDTime(), 16);
      for (var i = 0; i < 9; i++) {
        guidStr += Math.floor(Math.random()*16).toString(16);
      }
      guidStr += sexadecimalDate;
      guidStr += sexadecimalTime;
      while(guidStr.length < 32) {
        guidStr += Math.floor(Math.random()*16).toString(16);
      }
      return this.formatGUID(guidStr);
    }
 
    /*
     * ���ܣ���ȡ��ǰ���ڵ�GUID��ʽ����8λ�������ڣ�19700101
     * ����ֵ������GUID���ڸ�ʽ��������
     */
    GUID.prototype.getGUIDDate = function() {
      return this.date.getFullYear() + this.addZero(this.date.getMonth() + 1) + this.addZero(this.date.getDay());
    }
 
    /*
     * ���ܣ���ȡ��ǰʱ���GUID��ʽ����8λ����ʱ�䣬�������룬����Ϊ2λ����12300933
     * ����ֵ������GUID���ڸ�ʽ��������
     */
    GUID.prototype.getGUIDTime = function() {
      return this.addZero(this.date.getHours()) + this.addZero(this.date.getMinutes()) + this.addZero(this.date.getSeconds()) + this.addZero( parseInt(this.date.getMilliseconds() / 10 ));
    }
 
    /*
    * ����: Ϊһλ����������ǰ�����0������ǿ���ת�ɷ�NaN���ֵ��ַ���Ҳ����ʵ��
     * ����: ������ʾ׼����ǰ�����0�����ֻ����ת�������ֵ��ַ���
     * ����ֵ: ��������������������0������������ͣ����򷵻�������ַ���
     */
    GUID.prototype.addZero = function(num) {
      if (Number(num).toString() != 'NaN' && num >= 0 && num < 10) {
        return '0' + Math.floor(num);
      } else {
        return num.toString();
      }
    }
 
    /* 
     * ���ܣ���y���Ƶ���ֵ��ת��Ϊx���Ƶ���ֵ
     * ��������1��������ʾ��ת������ֵ����2��������ʾ��ת���Ľ��ƣ���3��������ѡ����ʾ��ǰ�Ľ��������粻д��Ϊ10
     * ����ֵ������ת������ַ���
     */
    GUID.prototype.hexadecimal = function(num, x, y) {
      if (y != undefined) {
        return parseInt(num.toString(), y).toString(x);
      } else {
        return parseInt(num.toString()).toString(x);
      }
    }
 
    /*
     * ���ܣ���ʽ��32λ���ַ���ΪGUIDģʽ���ַ���
     * ��������1��������ʾ32λ���ַ���
     * ����ֵ����׼GUID��ʽ���ַ���
     */
    GUID.prototype.formatGUID = function(guidStr) {
      var str1 = guidStr.slice(0, 8) ,
        str2 = guidStr.slice(8, 12) ,
        str3 = guidStr.slice(12, 16),
        str4 = guidStr.slice(16, 20),
        str5 = guidStr.slice(20);
      return str1 + str2 + str3 + str4 + str5;
    }
  }
}


 $.getUrlParam = function (name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if (r != null) return unescape(r[2]); return null;
}


