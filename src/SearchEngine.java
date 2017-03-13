
import java.util.*;
import main.controller.Controller;
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
		shell.setSize(399, 394);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(140, 84, 85, 27);
		
		//Initializing primary label
		Label lblAdvancedSearch = new Label(composite, SWT.NONE);
		lblAdvancedSearch.setFont(SWTResourceManager.getFont("Segoe UI Light", 17, SWT.NORMAL));
		lblAdvancedSearch.setAlignment(SWT.CENTER);
		lblAdvancedSearch.setBounds(10, 23, 345, 44);
		lblAdvancedSearch.setText("Advanced Search");
		
		//Initializing ScrolledComposite
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setBounds(10, 130, 363, 179);
		scrolledComposite.setExpandHorizontal(true);
		
		//Initializing Table
		Table table = new Table(scrolledComposite, SWT.BORDER);
		
		//Initializing TableColumn
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
	    TableColumn tc2 = new TableColumn(table, SWT.CENTER);
	    TableColumn tc3 = new TableColumn(table, SWT.CENTER);
	    tc1.setText("Title");
	    tc2.setText("URL");
	    tc3.setText("Status");
	    tc1.setWidth(100);
	    tc2.setWidth(150);
	    tc3.setWidth(100);
	    table.setHeaderVisible(true);

	    
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//Initializing Button
		Button btnSearch = new Button(composite, SWT.CENTER);
		
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
							result.addAll(controller.googleSearch(text.getText()));
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						//Add the Search result in Table
						for (int i = 0; i < result.size(); i++){
							TableItem item = new TableItem(table, SWT.NONE);
							item.setText(0, result.get(i).getTitle());
							item.setText(1, result.get(i).getURL());
							item.setText(2, result.get(i).getHttpStatus());
							
						}
					}
				}.run();
			}
		});
		btnSearch.setBounds(139, 320, 75, 25);
		btnSearch.setText("Search");
		
		
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
