# 🛠️ ARSW-2020 2 lab3
## Juan Romero - Andres Sotelo


## Introduction to Spring and Configuration using annotations
### Part I - Basic workshop

To illustrate the use of the Spring framework, and the development environment for its use through Maven (and NetBeans), the configuration of a text analysis application will be made, which makes use of a grammar verifier that requires a spelling checker. The grammar checker will be injected, at the time of execution, with the spelling checker required (for now, there are two available: English and Spanish).
                
                @Service("EnglishSpellChecker")
                public class EnglishSpellChecker implements SpellChecker {

                  @Override
                  public String checkSpell(String text) {		
                    return "Checked with english checker:"+text;
                  }


                }
          
              @Service("GrammarChecker")
              public class GrammarChecker {
                @Autowired
                @Qualifier("EnglishSpellChecker")
                SpellChecker sc;

                String x;
                
                public SpellChecker getSpellChecker() {
                  return sc;
                }

                public void setSpellChecker(SpellChecker sc) {
                  this.sc = sc;
                }


                public String check(String text){

                  StringBuffer sb=new StringBuffer();
                  sb.append("Spell checking output:"+sc.checkSpell(text));
                  sb.append("Plagiarism checking output: Not available yet");
                  return sb.toString();

                }


              }
              
Make a test program, where an instance of GrammarChecker is created by Spring, and use it:             
              
              public class Main{

              public static void main(String a[]) {
                  ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
                  GrammarChecker gc = ac.getBean(GrammarChecker.class);
                  System.out.println(gc.check("la la la "));
              }

          }
          
### Part II

Modify the configuration with annotations so that the Bean 'GrammarChecker' now makes use of the SpanishSpellChecker class (so that GrammarChecker is injected with EnglishSpellChecker instead of SpanishSpellChecker.) Verify the new result.

                @Service("SpanishSpellChecker")
                public class SpanishSpellChecker implements SpellChecker {

                  @Override
                  public String checkSpell(String text) {
                    return "revisando ("+text+") con el verificador de sintaxis del espanol";


                  }

                }
                
                
               @Service("GrammarChecker")
              public class GrammarChecker {
                 @Autowired
                 @Qualifier("SpanishSpellChecker")
                SpellChecker sc;

                String x;
                
                public SpellChecker getSpellChecker() {
                  return sc;
                }

                public void setSpellChecker(SpellChecker sc) {
                  this.sc = sc;
                }


                public String check(String text){

                  StringBuffer sb=new StringBuffer();
                  sb.append("Spell checking output:"+sc.checkSpell(text));
                  sb.append("Plagiarism checking output: Not available yet");
                  return sb.toString();

                }


              }
        
        
## Cinema Book System
### Description

1. In this exercise we will build a class model for the logical layer of an application that allows managing the sale of cinema tickets for a prestigious company.
Configure the application to work under a dependency injection scheme, as shown in the previous diagram. The above requires:
    - Add the dependencies of Spring. 
    - Add the Spring configuration. 
    - Configure the application -by means of annotations- so that the persistence scheme is injected at the moment of creation of the 'CinemaServices' bean.     
2. Complete the getCinemaByName (), buyTicket (), and getFunctionsbyCinemaAndDate () operations. Implement everything required from the lower layers (for now, the available persistence scheme 'InMemoryCinemasPersistence') by adding the corresponding tests in 'InMemoryPersistenceTest'.
3. For later queries, we want to implement two functionalities:
    - A method 'getFunctionsbyCinemaAndDate' that allows to obtain all the functions of a certain cinema for a certain date. 
    - Allow the purchase or reservation of tickets for a certain position of chairs in the room through the 'buyTicket' method. 
4. Make a program in which you create (through Spring) an instance of CinemaServices, and rectify the functionality of it: register cinemas, consult cinemas, obtain the functions of certain cinema, buy / book tickets, etc.
5. It is wanted that the consultations realize a filtering process of the films to exhibit, said filters look for to give him the facility to the user to see the most suitable films according to his necessity. Adjust the application (adding the abstractions and implementations that you consider) so that the CinemaServices class is injected with one of two possible 'filters' (or possible future filters). The use of more than one at a time is not contemplated:
      - (A) Filtered by gender: Allows you to obtain only the list of the films of a certain genre (of a certain cinema and a certain date) (The genre enters by parameter). 
      - (B) Filtering by availability: Allows you to obtain only the list of films that have more than x empty seats (of a certain cinema and a certain date) (The number of              seats is entered per parameter).
6. Add the corresponding tests to each of these filters, and test their operation in the test program, verifying that only by changing the position of the annotations -without changing anything else-, the program returns the list of films filtered in the manner (A ) or in the way (B).


### LOS ANTERIORES PUNTOS FUERON IMPLEMENTADO EN EL CODIGO DE ESTE REPOSITORIO

              
  
          
