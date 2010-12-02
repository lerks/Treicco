package treicco.shared;

import java.util.List;

import treicco.server.Task;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Task.class)
public interface TaskProxy extends EntityProxy {

	public String getId();

	public String getParent();

	public String getCodeName();

	public String getShortName();

	public void setShortName(String shortName);

	public String getFullName();

	public void setFullName(String fullName);

	public String getAuthor();

	public void setAuthor(String author);

	public String getTimelimit();

	public void setTimelimit(String timelimit);

	public String getMemorylimit();

	public void setMemorylimit(String memorylimit);

	public String getDifficulty();

	public void setDifficulty(String difficulty);

	public String getDescription();

	public void setDescription(String description);

	public List<ImageProxy> getImages();

	public String getInput();

	public void setInput(String input);

	public String getOutput();

	public void setOutput(String output);

	public String getConstraints();

	public void setConstraints(String constraints);

//	public List<ExampleProxy> getExamples();

//	public void setExamples(List<ExampleProxy> examples);

	public String getNotes();

	public void setNotes(String notes);
}
