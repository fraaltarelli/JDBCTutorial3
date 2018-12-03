<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inizio</title>
</head>
<body>

   <div  style="text-align:center;">
   
   <form action="CercaCitta">
   Cerca Città: <input type="text" name="cercacitta" size="10px"
				autocomplete="off">   </form>
				
				
				
				<c:if test= "${targetServletBottone == 'runNazioni'}">
				<a href="runNazioni">
				<button> indietro
				</button> <br>
				</a>
				</c:if>
				
				<c:if test= "${targetServletBottone == 'runContinenti'}">
				<a href="runContinenti">
				<button> continenti
				</button>  <br>
				</a>
				</c:if>
				<br>
				
				
				
				
				<c:if test="${newcitta ==true || currentCityId != 0}">
				<form action="runSaveCitta">		
		<table  width="75%" border="1" align="center">
         <tr>  <th width="34%">Seleziona la Nazione:</th> 
         <th with="22%">  Digita il nome della citta</th> 
         <th with="22%">  Digita il nome del distretto</th>
         <th with="22%">  Digita il numero di abitanti</th>
         </tr> 
          </table>		
   
  <table  width="75%" border="1" align="center">
         <tr>
         <td width="34%"> 
  <select name="nazioneselezionata">
  
  <c:forEach items="${allnations}" var ="nazione"> 
  <c:choose> <c:when test="${nazione==currentNation}">
  <option value="${nazione}" selected>${nazione}</option>
  </c:when>
  <c:otherwise>
    <option value="${nazione}">${nazione}</option>
    </c:otherwise>
    </c:choose>
    </c:forEach>
  </select>
   </td>
   
   <td width="22%">
   <input name="nomecittainserito"  type="text" value="${cittaDaModificare.nomeCitta}">
</td>
   
   <td width="22%">
   <input name="nomedistrettoinserito" type="text"  value="${cittaDaModificare.distretto}">  
   </td>
   <td width="22%">
   <input name="popolazioneinserita" type="number"  min="0" value="${cittaDaModificare.popolazione}">  
   </td>
   
   </tr>
   
   </table>
  
     <input name="creacitta" formaction="runSalvaCitta" type="submit" value="Salva">
     <br><br>
     
     </form>
     </c:if>
     
    
     <form action="runSalvaCitta">
     <input name="newcitta"  type="submit" value="NuovaCitta">
     </form>
     
     
     
     <form action="">
     <c:if test= "${listaCitta != null}">
         <table  width="75%" border="1" align="center">
         <tr>  <th width="30%">NomeCitta</th> <th width="26%">Distretto</th>
         <th width="26%">Popolazione</th>
         <th with="18%">Modifica Citta<th>
         </tr> 
          </table>
     
     <c:forEach items="${listaCitta}" var = "citta" >
     <table  width="75%" border="1" align="center">
         <tr>
         <td width="30%"> ${citta.nomeCitta} </td>
         <td width="26%"> ${citta.distretto} </td>
         <td width="26%"> ${citta.popolazione}</td>
         <td width="18%">
         <a href="runSalvaCitta?idcitta=${citta.id}"  class="button"> Modifica</a>
         </td>
         </tr>
         </table>
      </c:forEach>
      </c:if>
      
      
      <c:if test= "${ritornaCitta != null}">
         <table  width="75%" border="1" align="center">
         <tr>  <th width="35%">NomeCitta</th> <th width="33%">Distretto</th>
         <th width="32%">Popolazione</th>
         </tr> 
          </table>
      </c:if>
     <c:forEach items="${ritornaCitta}" var = "citta" >
     <table  width="75%" border="1" align="center">
         <tr>
         <td width="35%"> ${citta.nomeCitta} </td>
         <td width="33%"> ${citta.distretto} </td>
         <td width="32%"> ${citta.popolazione}</td>
         </tr>
         </table>
      </c:forEach>
     
     
     
     
</form>
				
   
   <c:if test="${ not empty listaContinenti }">
   <c:forEach items="${listaContinenti}" var = "continente" >
         <a href="runNazioni?idContinente=${continente}">
         
         ${continente} 
         
         </a><br>
    </c:forEach> 
    </c:if>
    
    
         
         <c:if test= "${listaNazioni != null }">
         <table  width="75%" border="1" align="center">
         <tr>  <th width="50%">NomeNazione</th> <th width="50%">Code</th>
         </tr> 
          </table>
 
    <c:forEach items="${listaNazioni}" var = "nazione" >
         <a href="runCitta?codiceNazioneSelezionata=${nazione.code}&nomeNazioneSelezionata=${nazione.nomeNazione}">
         <table  width="75%" border="1" align="center">
         <tr>
          <td width="50%"> ${nazione.nomeNazione} </td>
         <td width="50%"> ${nazione.code} </td> 
         </tr>   
         </table>
         </a>
      </c:forEach>
     </c:if>
      
       
       
     
     
     <br><br>
     
     
     
    
    
    
    
    </div>

</body>
</html>