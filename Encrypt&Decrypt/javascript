## javascript Encrypt logic
## encrypt each form element value 

var aesEnc = {
	init : function ( form ) {
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
		console.log( form.serialize() );
		for ( var i = 0 ; i < form.serializeArray().length ; i++ ) {
			var name = form.serializeArray()[ i ].name;
			console.log( name );
			console.log( form.find( "[name='" + name + "']" ) );
			console.log( CryptoJS.AES.encrypt( form.serializeArray()[ i ].value ,key ,{
				iv : iv
			} ).ciphertext.toString( CryptoJS.enc.Base64 ) );
			form.find( "[name='" + name + "']" ).val( CryptoJS.AES.encrypt( form.serializeArray()[ i ].value ,key ,{
				iv : iv
			} ).ciphertext.toString( CryptoJS.enc.Base64 ) );
		}
		form.append( '<input type="hidden" name="enc" value="Y"/>' );
	}
}


## how to use
## need parameter form element
...
aesEnc.init( form );
...
form.submit();




