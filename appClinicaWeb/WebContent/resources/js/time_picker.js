PrimeFaces.locales['es'] = {
	closeText : 'Cerrar',
	prevText : 'Anterior',
	nextText : 'Siguiente',
	monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
			'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
			'Diciembre' ],
	monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago',
			'Sep', 'Oct', 'Nov', 'Dic' ],
	dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves',
			'Viernes', 'Sábado' ],
	dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab' ],
	dayNamesMin : [ 'D', 'L', 'M', 'X', 'J', 'V', 'S' ],
	weekHeader : 'Semana',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Sólo hora',
	timeText : 'Tiempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	currentText : 'Fecha actual',
	ampm : false,
	month : 'Mes',
	week : 'Semana',
	day : 'Día',
	allDayText : 'Todo el día'
};

/* <![CDATA[ */
// Use case 2
function onHourShowCallback(hour) {
	if ((hour > 20) || (hour < 6)) {
		return false; // not valid
	}

	return true; // valid
}

function onMinuteShowCallback(hour, minute) {
	if ((hour == 20) && (minute >= 30)) {
		return false; // not valid
	}

	if ((hour == 6) && (minute < 30)) {
		return false; // not valid
	}

	return true; // valid
}

// Use case 3

function tpStartOnHourShowCallback(hour) {
	if (!PrimeFaces.widgets['endTimeWidget']) {
		return false;
	}

	var tpEndHour = parseInt(PF('endTimeWidget').getHours());

	// Check if proposed hour is prior or equal to selected end time hour
	if (parseInt(hour) <= tpEndHour) {
		return true;
	}

	// if hour did not match, it can not be selected
	return false;
}

function tpStartOnMinuteShowCallback(hour, minute) {
	if (!PrimeFaces.widgets['endTimeWidget']) {
		return false;
	}

	var tpEndHour = parseInt(PF('endTimeWidget').getHours());
	var tpEndMinute = parseInt(PF('endTimeWidget').getMinutes());

	// Check if proposed hour is prior to selected end time hour
	if (parseInt(hour) < tpEndHour) {
		return true;
	}

	// Check if proposed hour is equal to selected end time hour and minutes is
	// prior
	if ((parseInt(hour) == tpEndHour) && (parseInt(minute) < tpEndMinute)) {
		return true;
	}

	// if minute did not match, it can not be selected
	return false;
}

function tpEndOnHourShowCallback(hour) {
	if (!PrimeFaces.widgets['startTimeWidget']) {
		return false;
	}

	var tpStartHour = parseInt(PF('startTimeWidget').getHours());

	// Check if proposed hour is after or equal to selected start time hour
	if (parseInt(hour) >= tpStartHour) {
		return true;
	}

	// if hour did not match, it can not be selected
	return false;
}

function tpEndOnMinuteShowCallback(hour, minute) {
	if (!PrimeFaces.widgets['startTimeWidget']) {
		return false;
	}

	var tpStartHour = parseInt(PF('startTimeWidget').getHours());
	var tpStartMinute = parseInt(PF('startTimeWidget').getMinutes());

	// Check if proposed hour is after selected start time hour
	if (parseInt(hour) > tpStartHour) {
		return true;
	}

	// Check if proposed hour is equal to selected start time hour and minutes
	// is after
	if ((parseInt(hour) == tpStartHour) && (parseInt(minute) > tpStartMinute)) {
		return true;
	}

	// if minute did not match, it can not be selected
	return false;
}
/* ]]> */