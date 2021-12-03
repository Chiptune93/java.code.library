var includeJs = function (jsPath) {
  var js = document.createElement("script");
  js.defer = true;
  js.type = "text/javascript";
  js.src = jsPath;

  document.head.appendChild(js);
};

includeJs("/js/moment.min.js"); // 2.29.0
includeJs("/js/moment-timezone-with-data.js"); // 0.5.30

var util = {
  /**
   * 들어온 날짜 데이터를 포맷에 맞게 변환
   * @param {*} date
   * @param {1,?} type
   * @returns
   */
  formatDate: function (date, type) {
    var date_ = moment(date);
    var myYear = date_.format("YYYY");
    var myMonth = date_.format("MM");
    var myWeekDay = date_.format("DD");
    /* var addZero = function (num) {
      if (num < 10) {
        num = "0" + num;
      }
      return num;
    }; */
    if (type == 1) {
      var md = myYear + "년 " + myMonth + "월";
    } else {
      var md = myYear + "." + myMonth + "." + myWeekDay;
    }
    return md;
  },
  /**
   * 들어온 날짜 데이터 포맷에 맞게 변환 + 요일
   * @param {*} date
   * @param {1,2,3,4} type
   * @returns
   */
  formatDateWithDay: function (date, type) {
    var date_ = moment(date);
    const WEEKDAY = ["일", "월", "화", "수", "목", "금", "토"];
    var myYear = date_.format("YYYY");
    var myMonth = date_.format("MM");
    var myWeekDay = date_.format("DD");
    var myDay = WEEKDAY[date_.day()];
    var md = myYear + "." + myMonth + "." + myWeekDay + " " + myDay + "요일";
    if (type == 1) {
      var md = myYear + "." + myMonth + "." + myWeekDay + " (" + myDay + ")";
    } else if (type == 2) {
      var md = myYear + "." + myMonth + "." + myWeekDay + " (" + myDay + "요일)";
    } else if (type == 3) {
      var md = myMonth + "." + myWeekDay + " (" + myDay + ")";
    } else if (type == 4) {
      var md = myYear + "년 " + myMonth + "월 " + myWeekDay + "일 " + myDay + "요일";
    } else {
      var md = myYear + "." + myMonth + "." + myWeekDay + " " + myDay + "요일";
    }
    return md;
  },
  /**
   * 문자열로 처리해 subString
   * @param {*} val
   * @param {*} s
   * @param {*} e
   * @returns
   */
  substring: function (val, s, e) {
    var origin = val + "";
    return origin.substring(s, e);
  },
  /**
   * 문자열로 처리해 split
   * @param {*} val
   * @param {*} v
   * @param {*} i
   * @returns
   */
  split: function (val, v, i) {
    var origin = val + "";
    let arr = origin.split(v);
    return arr[i];
  },
  /**
   * 타입과 int 숫자를 받아 현재 주(월) 포함 날짜 시작일/종료일 리턴.
   * @param {month,week} type
   * @param {*} cnt
   * @returns
   */
  getSearchCalDate: function (type, incYn, cnt, calType, date) {
    let result = new Map();
    /* 이번 주 포함으로 계산하기위해  */
    if (incYn != "Y") {
      cnt = cnt;
    } else {
      cnt -= 1;
    }
    var now = new Date();
    if (date != null && date != "undefined") {
      var now = new Date(date);
    } else {
      var now = new Date();
    }
    var nowDayOfWeek = now.getDay();
    var nowDay = now.getDate();
    var nowMonth = now.getMonth();
    var nowYear = now.getFullYear();
    var nowYear2 = now.getFullYear();
    var stDate;
    var edDate;
    var sty;
    var stm;
    var edy;
    var edm;
    if (type == "month") {
      if (calType == "plus") {
        // 예외 처리.
        if (cnt > 0) {
          stDate = new Date(nowYear, nowMonth + 1, 1);
          sty = nowYear;
          stm = nowMonth + 1;
        } else {
          stDate = new Date(nowYear, nowMonth, 1);
          sty = nowYear;
          stm = nowMonth;
        }
        if (incYn != "Y") {
          edDate = new Date(nowYear, nowMonth + cnt + 1, 0);
          edy = nowYear;
          edm = nowMonth + cnt + 1;
        } else {
          edDate = new Date(nowYear, nowMonth + 1, 0);
          edy = nowYear;
          edm = nowMonth + 1;
        }
      } else {
        // 예외 처리.
        if (nowMonth - cnt < 0) nowYear -= 1;
        if (cnt > 0) {
          stDate = new Date(nowYear, nowMonth - cnt, 1);
          sty = nowYear;
          stm = nowMonth - cnt;
        } else {
          stDate = new Date(nowYear, nowMonth, 1);
          sty = nowYear;
          stm = nowMonth;
        }
        if (incYn != "Y") {
          edDate = new Date(nowYear2, nowMonth, 0);
          edy = nowYear2;
          edm = nowMonth;
        } else {
          edDate = new Date(nowYear2, nowMonth + 1, 0);
          edy = nowYear2;
          edm = nowMonth + 1;
        }
      }
    } else {
      if (calType == "plus") {
        if (cnt > 0) {
          // 예외처리
          if (nowMonth == 11) {
            if (nowDay - nowDayOfWeek + 1 + cnt * 7 > 31) {
              nowYear += 1;
            }
          }
          stDate = new Date(nowYear2, nowMonth, nowDay - nowDayOfWeek + 1);
          sty = nowYear2;
          stm = nowMonth;
        } else {
          stDate = new Date(nowYear2, nowMonth, nowDay - nowDayOfWeek + 1);
          sty = nowYear2;
          stm = nowMonth;
        }
        if (incYn != "Y") {
          // 시작일의 전주 종요일.
          edDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek + cnt * 7);
          edy = nowYear;
          edm = nowMonth;
        } else {
          // 현재 주 마지막 일이 종료일이 됨.
          edDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek + 1));
          edy = nowYear;
          edm = nowMonth;
        }
      } else {
        if (cnt > 0) {
          // 예외처리
          if (nowMonth == 0) {
            if (nowDay - nowDayOfWeek + 1 - cnt * 7 < 1) {
              nowYear -= 1;
            }
          }
          stDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek + 1 - cnt * 7);
          sty = nowYear;
          stm = nowMonth;
        } else {
          stDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek + 1);
          sty = nowYear;
          stm = nowMonth;
        }
        if (incYn != "Y") {
          // 시작일의 전주 종요일.
          edDate = new Date(nowYear2, nowMonth, nowDay + (6 - nowDayOfWeek + 1) - 1 * 7);
          edy = nowYear2;
          edm = nowMonth;
        } else {
          // 현재 주 마지막 일이 종료일이 됨.
          edDate = new Date(nowYear2, nowMonth, nowDay + (6 - nowDayOfWeek + 1));
          edy = nowYear2;
          edm = nowMonth;
        }
      }
    }
    var st = comm.formatDate(stDate);
    var ed = comm.formatDate(edDate);
    result.set("st", st);
    result.set("sty", sty);
    result.set("stm", stm);
    result.set("ed", ed);
    result.set("edy", edy);
    result.set("edm", edm);
    return result;
  },
  /**
   * millisecond 를 타입에 맞게 문자열로 반환
   * @param {millisecond} duration
   * @param {*} type
   * @returns
   */
  millToHm: function (duration, type) {
    var result;
    if (duration == "undefined" || duration == null || duration < 0) {
      if (type == 1) {
        result = "00H 00M";
      } else if (type == 2) {
        result = "0:00";
      } else if (type == 3) {
        result = "00:00:00";
      } else {
        result = "00H 00M";
      }
    } else {
      var milliseconds = Math.floor((duration % 1000) / 100),
        seconds = Math.floor((duration / 1000) % 60),
        minutes = Math.floor((duration / (1000 * 60)) % 60),
        hours = Math.floor(duration / (1000 * 60 * 60));

      hours = hours < 10 ? "0" + hours : hours;
      minutes = minutes < 10 ? "0" + minutes : minutes;
      seconds = seconds < 10 ? "0" + seconds : seconds;
      if (type == 1) {
        result = hours + "H " + minutes + "M";
      } else if (type == 2) {
        result = hours + ": " + minutes;
      } else if (type == 3) {
        result = hours + ":" + minutes + ":" + seconds;
      } else {
        result = hours + "H " + minutes + "M";
      }
    }
    return result;
  },
  /**
   * millisecond 를 시간으로 반환
   * @param {millisecond} duration
   * @param {*} type
   * @returns
   */
  millToHour: function (duration, type) {
    var result;
    if (duration == null || duration == "" || duration == "0") {
      result = "0";
    } else {
      result = duration / (1000 * 60 * 60);
    }
    return Math.floor(result, 1);
  },
  /**
   * 두 날짜의 차이를 일수로 반환
   * @param {*} date1
   * @param {*} date2
   * @returns
   */
  dateDiff: function (date1, date2) {
    var date1_ = new Date(date1.substring(0, 4), date1.substring(5, 7), date1.substring(8, 10));
    var date2_ = new Date(date2.substring(0, 4), date2.substring(5, 7), date2.substring(8, 10));
    var betweenDate_ = Math.abs(date2_.getTime() - date1_.getTime());
    return Math.floor(betweenDate_ / (1000 * 60 * 60 * 24));
  },
  /**
   * UTC 시간대 데이터를 Asia 시간대로 변경
   * @param {UTC date} date
   * @param {*} type
   * @returns
   */
  utcToAsia: function (date, type) {
    var result;
    if (type == 1) {
      if (date == null || date == "") {
        result = "00:00:00";
      } else {
        result = moment.utc(date).tz("Asia/Seoul").format("HH:mm:ss");
      }
    } else if (type == 2) {
      if (date == null || date == "") {
        result = "00:00";
      } else {
        result = moment.utc(date).tz("Asia/Seoul").format("HH:mm");
      }
    } else if (type == 3) {
      if (date == null || date == "") {
        result = "";
      } else {
        result = moment.utc(date).tz("Asia/Seoul").format("HH:mm");
      }
    } else if (type == 4) {
      if (date == null || date == "") {
        result = "00:00";
      } else {
        result = moment.utc(date).tz("Asia/Seoul").format("YYYY.MM.DD HH:mm");
      }
    } else {
      if (date == null || date == "") {
        result = "00:00";
      } else {
        result = moment.utc(date).tz("Asia/Seoul").format("HH:mm");
      }
    }
    return result;
  },
  /**
   * null/undefined/'' 값이 들어올 경우 0 으로 치환한다.
   * @param {*} value
   * @returns
   */
  nullToZero: function (value) {
    if (value == null || value == "undefined" || value == "") {
      return 0;
    } else {
      return value;
    }
  },
  /**
   * 들어온 두 날짜 사이의 시간을 지정된 형식에 맞게 반환
   * @param {*} start
   * @param {*} end
   * @returns
   */
  hhmmDiff: function (start, end) {
    var date1 = new Date();
    var date2 = new Date();

    date1.setHours(comm.split(start, ":", 0));
    date2.setHours(comm.split(end, ":", 0));

    date1.setMinutes(comm.split(start, ":", 1));
    date2.setMinutes(comm.split(end, ":", 1));

    date1.setSeconds(0);
    date2.setSeconds(0);

    var mill = date2 - date1;

    if (isNaN(mill)) {
      return "00H 00M";
    } else {
      return util.millToHm(mill);
    }
  },
  /**
   * HH:mm 시간대 형식 체크
   * @param {*} val
   * @returns
   */
  timeValidateChk: function (val) {
    var chk = /(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])/;
    return chk.test(val);
  },
  /**
   * 들어온 날짜에 일 수를 더하거나 뺀다
   * @param {*} date
   * @param {*} type
   * @param {*} cnt
   * @returns
   */
  stringDateCal: function (date, type, cnt) {
    var d = new Date(date);
    if (type == "+") {
      d.setDate(d.getDate() + cnt);
      return d;
    } else {
      d.setDate(d.getDate() - cnt);
      return d;
    }
  },
  /**
   * 시작 시간과 종료 시간을 맵으로 받아 시작시간이 종료시간보다 전인지 체크한다.
   * @param {map[starttime: s finishTime: f]} timeArr
   * @returns
   */
  chkTimeList: function (timeArr) {
    var s = timeArr.get("s");
    var f = timeArr.get("f");

    var date1 = new Date();
    var date2 = new Date();

    date1.setHours(comm.split(s, ":", 0));
    date2.setHours(comm.split(f, ":", 0));

    date1.setMinutes(comm.split(s, ":", 1));
    date2.setMinutes(comm.split(f, ":", 1));

    date1.setSeconds(0);
    date2.setSeconds(0);

    var mill = date2 - date1;
    console.log("🚀 ~ file: common.js ~ line 1529 ~ mill", mill);

    if (mill > 0) {
      return true;
    } else if (mill < 0) {
      return false;
    } else {
      return false;
    }
  },
  /**
   * 시작시간과 종료시간이 들어있는 Map 을 받아 겹치는 시간이 있는지 체크 한다.
   * @param {map [ startTime : s / finishTime : f ]}} timeArr
   * @returns
   */
  duplicateChkTimeList: function (timeArr) {
    var dupCnt = 0;
    for (var i = 0; i < timeArr.length; i++) {
      for (var j = 0; j < timeArr.length; j++) {
        var s = timeArr[i].get("s");
        var f = timeArr[i].get("f");
        if (j != i) {
          var s_ = timeArr[j].get("s");
          var f_ = timeArr[j].get("f");
          // console.log("─────────────────────────────────");
          // console.log("기준시 : " + s + " ~ " + f);
          // console.log("비교시 : " + s_);
          // console.log("rs --> " + (s < s_));
          // console.log("rs --> " + (s_ < f));
          // console.log("─────────────────────────────────");
          // console.log("비교시 : " + f_);
          // console.log("rs --> " + (s < f_));
          // console.log("rs --> " + (f_ < f));
          // console.log("─────────────────────────────────");
          if ((s < s_ && s_ < f) || (s < f_ && f_ < f)) {
            dupCnt++;
          }
        }
      }
    }
    return dupCnt;
  },
  /**
   * 시작 날짜와 종료 날짜이 들어있는 Map 을 받아 내부에 포함되는 시간이 있는지 체크 한다.
   * @param {map [ startDate : s / finishDate : f ]}} timeArr
   * @returns
   */
  duplicateChkDateList: function (dateArr) {
    var dupCnt = 0;
    for (var i = 0; i < dateArr.length; i++) {
      for (var j = 0; j < dateArr.length; j++) {
        var s = moment(dateArr[i].get("s"));
        var f = moment(dateArr[i].get("f"));
        if (j != i) {
          var s_ = moment(dateArr[j].get("s"));
          var f_ = moment(dateArr[j].get("f"));
          console.log("─────────────────────────────────");
          console.log("기준시 : " + s + " ~ " + f);
          console.log("비교시 : " + s_);
          console.log("rs --> " + (s < s_));
          console.log("rs --> " + (s_ < f));
          console.log("─────────────────────────────────");
          console.log("비교시 : " + f_);
          console.log("rs --> " + (s < f_));
          console.log("rs --> " + (f_ < f));
          console.log("─────────────────────────────────");
          if ((s <= s_ && s_ <= f) || (s <= f_ && f_ <= f)) {
            dupCnt++;
          }
        }
      }
    }
    return dupCnt;
  },
  /**
   * 세자리 마다 콤마 삽입
   * @param {number} val
   * @returns
   */
  putComma: function (val) {
    if (val == null) return "0";
    return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  },
  /**
   * - 를 치환 한다.
   * @param {*} obj
   * @returns
   */
  replaceDash: function (obj) {
    return (obj.value = obj.value.toString().replace("-", ""));
  },
  /**
   * 숫자만 입력된 obj 인지 체크 하고, 길이까지 체크한다.
   * @param {*}} obj
   * @param {*} length
   */
  chkNumberAndLength: function (obj, length) {
    var regExp = /(^[\d]+$){1}/g;
    if (!obj.value.match(regExp) || obj.value.length > length) {
      obj.value = "";
    }
  },
  /**
   * 검색 오브젝트에 영어/한글/숫자만 들어오는지 판단한다.
   * @param {*} obj
   */
  chkSearchText: function (obj) {
    var regExp = /^[ㄱ-ㅎㅏ-ㅣ가-힣|a-zA-Z|0-9]*$/;
    if (!obj.value.match(regExp)) {
      obj.value = "";
    }
  },
};
