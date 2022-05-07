## javascript Encrypt logic
## encrypt each form element value -> create new form and set encrypt value for submit new Encrypt Form 

var aesEnc = {
	init : function ( form ,actionType ) {
		console.log( "──────── AES ENC ────────" );
		// required key
		// in this case, use jsessionid & session storage
		$.ajax( {
			"url" : "/getReqJid.do" ,
			"method" : "POST" ,
			"dataType" : "json" ,
			"async" : false ,
			"data" : null ,
			"success" : function ( data ) {
				sessionStorage.setItem( "jid" ,data[ "result" ] );
				var jid = sessionStorage.getItem( "jid" );
				var key = "";
				if ( jid != null ) {
					if ( jid instanceof Array ) key = jid[ 0 ].substring( 0 ,16 );
					else key = jid.substring( 0 ,16 );
				}
				const iv = CryptoJS.enc.Utf8.parse( key );
				const key_real = CryptoJS.enc.Utf8.parse( key );
				aesEnc.encData( form ,iv ,key_real );
				sessionStorage.removeItem( "jid" );
				return true;
			}
		} );
		console.log( "──────── AES ENC ────────" );
		return true;
	} ,
	encData : function ( form ,iv ,key ) {
		// append new form
		$( body ).append( '<form name="encAppendForm" id="encAppendForm"></form>' );
		// add enc yn
		$( "#encAppendForm" ).append( '<input type="hidden" name="enc" value="Y"/>' );
		for ( var i = 0 ; i < form.serializeArray().length ; i++ ) {
			var name = form.serializeArray()[ i ].name;
			var type = form.find( "[name='" + name + "']" ).prop( "type" );
			var val = form.serializeArray()[ i ].value;
			var esp_val = "libart_" + form.serializeArray()[ i ].value;
			var encVal = CryptoJS.AES.encrypt( val ,key ,{
				iv : iv
			} ).ciphertext.toString( CryptoJS.enc.Base64 );
			// make encVal to hidden Form value and append encForm
			aesEnc.appendFormData( name ,encVal );
		}
	} ,
	appendFormData : function ( name ,val ) {
		// append form function
		var encForm = $( "#encAppendForm" );
		encForm.append( '<input type="hidden" name="' + name + '" value="' + val + '" />' );
	} ,
	submitEncForm : function ( url ) {
		// replace original form submit function
		$( "#encAppendForm" ).attr( "action" ,url );
		$( "#encAppendForm" ).submit();
	}
}

## how to use
## need parameter form element
...
aesEnc.init( form );
...
aesEnc.submitEncForm( url );

