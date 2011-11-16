package aurelienribon.tweenstudio;

import aurelienribon.tweenengine.Tweenable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public abstract class Editor {
	private final Map<Class<? extends Tweenable>, List<Property>> propertiesMap = new HashMap<Class<? extends Tweenable>, List<Property>>();

	public abstract void initialize();
	public abstract void setFileContent(String filepath, String content);
	public abstract String getFileContent(String filepath);

	public List<Property> getProperties(Class<? extends Tweenable> clazz) {
		assert propertiesMap.containsKey(clazz);
		return propertiesMap.get(clazz);
	}

	public Property getProperty(Class<? extends Tweenable> clazz, int tweenType) {
		List<Property> properties = getProperties(clazz);
		for (Property property : properties)
			if (property.getId() == tweenType)
				return property;
		assert false;
		return null;
	}

	protected void registerProperty(Class<? extends Tweenable> clazz, int propertyId, String propertyName) {
		if (!propertiesMap.containsKey(clazz))
			propertiesMap.put(clazz, new ArrayList<Property>(5));
		propertiesMap.get(clazz).add(new Property(propertyId, propertyName));
	}
}
