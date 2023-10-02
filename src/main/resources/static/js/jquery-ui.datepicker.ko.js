jQuery(function ($) {
  $.datepicker.regional['ko'] = {
    closeText: 'Done',
    prevText: '이전달',
    nextText: '다음달',
    currentText: '오늘',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    weekHeader: 'Wk',
    dateFormat: 'yy-mm-dd',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: true,
    yearSuffix: '  ',

    changeYear: true,
    changeMonth: true,
    showOtherMonths: true,
    selectOtherMonths: true
  };

  $.datepicker.setDefaults($.datepicker.regional['ko']);
});