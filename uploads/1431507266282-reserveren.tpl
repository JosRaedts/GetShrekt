    {include file="header_child.tpl"}
        {assign background_image array_slice($content['medias'][0], 0, 10)}
        <div class="foto_header container">
		     <div class="row">
                <div class="col-xs-12">
                    <h1>{$content['title'] nofilter}</h1>
                </div>
            </div>
		</div>
        <div class="pages white paralax foto" id="page-{$content['id']}" style="background-image: url('{$uploaduri}/media/{$background_image[0]['hash']}.{$background_image[0]['extension']}');"></div>
        <div class="pages champagne" id="page-{$content['id']}">

        <div class="container">

 <div class="col-sm-6">
                    <h1>{$content['title'] nofilter}</h1>
					<p>{$content['text'] nofilter}</p>
                    <p>{$content['form']['html'] nofilter}</p>
                </div>
                <div class="col-sm-6"><br /><br /><br />

                      <p><strong>Openingstijden:</strong><br />
                      1.)  Bij onze maandelijkse thema gerichte avonden staat de openings-en aanvangstijd erbij vermeld.</p>
                  <p>2.)  Wij zijn open op uw gewenste dag en tijd na reservatie,  voor al uw beleef mogelijkheden vanaf tien  personen.</p>
                  <p><strong>Betaalmogelijkheden  bij LUST:</strong> </p>
<li> Contant</li>
<li> Pinautomaat</li>
<li> De Beleefbon van LUST (bij ons af te halen)</li>
<li> De Nationale Horeca Cadeaukaart (ook bij ons verkrijgbaar)</li>
<li> Eurocard-Mastercard / Visa-card</li>
<li>  Op rekening, alleen bedrijfmatig;<br />na een telefonische en schriftelijke bevestiging van de desbetreffende administratie van het bedrijf.</li>
<br /><br />
                </div>
	
</div>
    
   </div>
    {include file="footer.tpl"}



