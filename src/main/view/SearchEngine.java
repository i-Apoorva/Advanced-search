package main.view;



import java.util.*;
import main.controller.Controller;
import main.engines.GoogleSearch;
import main.model.SearchResult;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class SearchEngine{
	
	private final Shell shell;
	private final Display display;
	private Controller controller;
	private Text text;
	private Table table;
	
	public SearchEngine(){
		
		//Initializing Frame and Controller
		controller = new Controller();
		display = new Display();
		shell = new Shell(display);
		shell.setMinimumSize(new Point(136, 60));
		shell.setBackgroundImage(SWTResourceManager.getImage(SearchEngine.class,"/main/background3.png"));
		shell.setSize(1020, 800);
		RowLayout rowLayout = new RowLayout();
		shell.setLayout(rowLayout);
		rowLayout.wrap = true;
		rowLayout.justify=true;
		rowLayout.pack=true;
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new RowData(997, 800));
		composite.setBackgroundImage(SWTResourceManager.getImage(SearchEngine.class, "/main/background3.png"));
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(65, 111, 757, 33);
		
		//Initializing primary label
		Label lblAdvancedSearch = new Label(composite, SWT.NONE);
		lblAdvancedSearch.setBackground(SWTResourceManager.getColor(255, 248, 220));
		lblAdvancedSearch.setImage(SWTResourceManager.getImage(SearchEngine.class, "/main/logonew.png"));
		lblAdvancedSearch.setFont(SWTResourceManager.getFont("Segoe UI Light", 17, SWT.NORMAL));
		lblAdvancedSearch.setAlignment(SWT.CENTER);
		lblAdvancedSearch.setBounds(269, 38, 428, 71);
		
		//Initializing Button
		Button btnSearch = new Button(composite, SWT.CENTER);
		//Initializing Table
				Table table_1 = new Table(composite, SWT.BORDER);
				table_1.setBounds(65, 156, 838, 563);
				
		//Adding Click Event Listener to btnSearch
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				//Run a thread to search
				new Runnable(){
					@Override
					public void run(){
						
						ArrayList<SearchResult> result = new ArrayList<SearchResult>();
						try {
							result.addAll(controller.googleSearch(text.getText(),GoogleSearch.DEFAULT_USER_AGENT));
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						//Add the Search result in Table
						for (int i = 0; i < result.size(); i++){
							TableItem item = new TableItem(table_1, SWT.NONE);
							item.setText(0, result.get(i).getTitle());
							item.setText(1, result.get(i).getURL());
							item.setText(2, result.get(i).getHttpStatus());
							
						}
					}
				}.run();
			}
		});
		btnSearch.setBounds(828, 111, 75, 33);
		btnSearch.setText("Search");
		
		
		//Initializing TableColumn
		TableColumn tc1 = new TableColumn(table_1, SWT.CENTER);
		TableColumn tc2 = new TableColumn(table_1, SWT.CENTER);
		TableColumn tc3 = new TableColumn(table_1, SWT.CENTER);
		tc1.setText("Title");
		tc2.setText("URL");
		tc3.setText("Status");
		tc1.setWidth(400);
		tc2.setWidth(300);
		tc3.setWidth(150);
		table_1.setHeaderVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmConfiguration = new MenuItem(menu, SWT.NONE);
		mntmConfiguration.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				ConfigDialog config = new ConfigDialog(shell);
				
				config.open();
				
				GoogleSearch.DEFAULT_USER_AGENT = config.getUserAgent();
				
			}
		});
		mntmConfiguration.setText("Configuration");
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("About");
		
		
		shell.open();
		
		// run the event loop as long as the window is open
				while (!shell.isDisposed()) {
				    // read the next OS event queue and transfer it to a SWT event
				    if (!display.readAndDispatch())
				     {
				    // if there are currently no other OS event to process
				    // sleep until the next OS event is available
				        display.sleep();
				     }
				}

				// disposes all associated windows and their components
				display.dispose();

	}
	
	public static void main(String[] args) throws Exception{
		new SearchEngine();
    }
}
