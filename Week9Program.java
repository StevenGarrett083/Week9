import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Week9Program extends Application {
	
	@Override
	public void start( Stage stage ) {
		
		Week9Program a = new Week9Program( );
		ArrayList< String > commands = a.read( getParameters( ).getRaw( ).get( 0 ) );
		Pane pane = new Pane( );


		Scene scene = null;
		int j = 0;

		for ( int i = 0; i < commands.size( ); i++ ) {
			
			if ( !commands.get( i ).equals( "END" ) ) {
				j++;
			}
			else {
				break;
			}
		}
		
		if ( j == commands.size( ) ) {
			System.out.println( "Please put END at the end of the file!" );
			System.exit( 0 );
		}
		
		for ( int i = 0; i < commands.size( ); i++ ) {
			
			if ( commands.get( i ).equals( "SIZE" ) ) {
				
				try {
					i++;	
					Double w = Double.parseDouble( commands.get( i ) );
					i++;
					Double h = Double.parseDouble( commands.get( i ) );
					scene = new Scene( pane, w, h );
					scene.setFill( Color.BLACK );
				} catch ( NumberFormatException e ) {
					System.out.println( "There's an error with the SIZE command" );
				}
			
			}

			if ( commands.get( i ).equals( "LINE" ) ) {
				
				try {
					i++;
					Double x1 = Double.parseDouble( commands.get( i ) );
					i++;
					Double y1 = Double.parseDouble( commands.get( i ) );
					i++;
					Double x2 = Double.parseDouble( commands.get( i ) );
					i++;
					Double y2 = Double.parseDouble( commands.get( i ) );
					Line line = new Line( x1, y1, x2, y2 );
					line.setStroke( Color.rgb( 127, 244, 16 ) );
					pane.getChildren( ).add( line ); 
				} catch ( NumberFormatException e ) {
					System.out.println( "There's an error with a LINE command" );
				}
			}

			if ( commands.get( i ).equals( "CIRCLE" ) ) {
				
				try {
					i++;
					Double x = Double.parseDouble( commands.get( i ) );
					i++;
					Double y = Double.parseDouble( commands.get( i ) );
					i++;
					Double r = Double.parseDouble( commands.get( i ) );
					Circle circle = new Circle( x, y, r );
					circle.setStroke( Color.rgb( 127, 244, 16 ) );
					pane.getChildren( ).add( circle );
				} catch ( NumberFormatException e ) {
					System.out.println( "There's an error with a CIRCLE command" );
					System.exit( 0 );
				}
			}

			if ( commands.get( i ).equals( "RECTANGLE" ) ) {

				try {
					i++;
					Double x = Double.parseDouble( commands.get( i ) );
					i++;
					Double y = Double.parseDouble( commands.get( i ) );
					i++;
					Double w = Double.parseDouble( commands.get( i ) );
					i++;
					Double h = Double.parseDouble( commands.get( i ) );
					Rectangle rectangle = new Rectangle( x, y, w, h );
					rectangle.setFill( Color.BLACK );
					rectangle.setStroke( Color.rgb( 127, 244, 16 ) );
					pane.getChildren( ).add( rectangle );
				} catch ( NumberFormatException e ) {
					System.out.println( "There's an error with a RECTANGLE command" );
					System.exit( 0 );
				}
			}

			if ( commands.get( i ).equals( "ARC" ) ) {

				try {
					i++;
					Double cx = Double.parseDouble( commands.get( i ) );
					i++;
					Double cy = Double.parseDouble( commands.get( i ) );
					i++;
					Double rx = Double.parseDouble( commands.get( i ) );
					i++;
					Double ry = Double.parseDouble( commands.get( i ) );
					i++;
					Double sa = Double.parseDouble( commands.get( i ) );
					i++;
					Double l = Double.parseDouble( commands.get( i ) );
					i++;
					Arc arc = new Arc( cx, cy, rx, ry, sa, l );
					if ( commands.get( i ).equals( "OPEN" ) ) {
						arc.setType( ArcType.OPEN );
					}
					if ( commands.get( i ).equals( "CHORD" ) ) {
						arc.setType( ArcType.CHORD );
					}
					if ( commands.get( i ).equals( "ROUND" ) ) {
						arc.setType( ArcType.ROUND );
					}
					arc.setFill( Color.BLACK );
					arc.setStroke( Color.rgb( 127, 244, 16 ) );
					pane.getChildren( ).add( arc );
					i--;
				} catch ( NumberFormatException e ) {
					System.out.println( "There's an error with an ARC command" );
					System.exit( 0 );
				}
			}

			if ( commands.get( i ).equals( "POLYGON" ) ) {
				
				Polygon polygon = new Polygon( );
				ArrayList< Double > polyList = new ArrayList< Double > ( );
				try {
					i++;	
					while ( !commands.get( i ).equals( "LINE" ) &&
						!commands.get( i ).equals( "CIRCLE" ) && 
						!commands.get( i ).equals( "RECTANGLE" ) &&
				       		!commands.get( i ).equals( "TEXT" ) &&
				       		!commands.get( i ).equals( "SCENE" ) &&
						!commands.get( i ).equals( "END" ) &&
						!commands.get( i ).equals( "ARC" ) &&
						!commands.get( i ).equals( "POLYGON" ) &&
						!( ( i - 1 ) == commands.size( ) ) ) {
							polyList.add( Double.parseDouble( commands.get( i ) ) );
							i++;
						}
					polygon.setFill( Color.BLACK );
					polygon.setStroke( Color.rgb( 127, 244, 16 ) );
					polygon.getPoints().addAll( polyList );
					pane.getChildren( ).add( polygon );
					polyList.clear( );
					i--;
				} catch ( NumberFormatException e ) {
					System.out.println( "There's an error with a POLYGON command" );
					System.exit( 0 );
				}
			}

			if ( commands.get( i ).equals( "TEXT" ) ) {
				
				try {
					i++;
					Double x = Double.parseDouble( commands.get( i ) );
					i++;
					Double y = Double.parseDouble( commands.get( i ) );
					i++;
					StringBuffer sb = new StringBuffer( );
	
					while ( !commands.get( i ).equals( "LINE" ) &&
						!commands.get( i ).equals( "CIRCLE" ) && 
						!commands.get( i ).equals( "RECTANGLE" ) &&
				       		!commands.get( i ).equals( "TEXT" ) &&
				       		!commands.get( i ).equals( "SCENE" ) &&
						!commands.get( i ).equals( "END" ) &&
						!commands.get( i ).equals( "ARC" ) &&
						!commands.get( i ).equals( "POLYGON" ) &&
						!( ( i - 1 ) == commands.size( ) ) ) {
			
						sb.append( commands.get( i ) );
						sb.append( " " );
						i++;
					
					}

					String str = sb.toString( );
					Text text = new Text( x, y, str );
					text.setStroke( Color.rgb( 127, 244, 16 ) );
					pane.getChildren( ).add( text );
					i--;
				} catch ( NumberFormatException e ) {
					System.out.println( "There's an error with a TEXT command" );
					System.exit( 0 );
				}
			}

			if ( commands.get( i ).equals( "END" ) ) {
				break;
			}

		}
		
		stage.setTitle( "Week 9 Program" );
		stage.setScene( scene );
		stage.show( );
	}


	public ArrayList<String> read ( String file ) {


		ArrayList< String > cmds = new ArrayList< String > ( );

		File fin = new File ( file );
		Scanner scan = null;
		try {
			scan = new Scanner( fin );
			while ( scan.hasNext( ) ) {
				String cmd = scan.next( );
				cmds.add( cmd );
			}
		} catch ( FileNotFoundException e ) {
			System.out.println( "File not found!" );
			System.exit( 0 );
		} finally {
			if ( scan != null ) scan.close( );
		}	
		
		scan.close( );
		return cmds;
	}

	public static void main ( String [ ] args ) {

		Application.launch( args );
	}
}


