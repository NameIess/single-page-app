package resources;

import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.CreatingCriteria;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.dto.request.SearchingCriteria;
import com.training.epam.entity.dto.request.UpdatingCriteria;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import resources.factory.CompositeFactory;
import resources.factory.JsonDtoFactory;
import resources.factory.impl.CompositeFactoryImpl;
import resources.factory.impl.CreatingCriteriaFactory;
import resources.factory.impl.SearchCriteriaFactory;
import resources.factory.impl.UpdatingCriteriaFactory;

import java.util.ArrayList;
import java.util.List;

public class TestResource {
    public static final JsonDtoFactory<SearchingCriteria> SEARCHING_CRITERIA_FACTORY = new SearchCriteriaFactory();
    public static final JsonDtoFactory<CreatingCriteria> CREATING_CRITERIA_FACTORY = new CreatingCriteriaFactory();
    public static final JsonDtoFactory<UpdatingCriteria> UPDATING_CRITERIA_FACTORY = new UpdatingCriteriaFactory();
    public static final CompositeFactory COMPOSITE_FACTORY = new CompositeFactoryImpl();
    public static final SearchingCriteria SEARCHING_CRITERIA = SEARCHING_CRITERIA_FACTORY.create("search");
    public static final CreatingCriteria CREATING_CRITERIA = CREATING_CRITERIA_FACTORY.create();
    public static final UpdatingCriteria UPDATING_CRITERIA = UPDATING_CRITERIA_FACTORY.create("id", "target");
    public static final int ONE_TIME = 1;
    public static final String EMPTY_STRING = "";
    public static final String POSITIVE_BORDERED_MIN_LENGTH_CELL_STRING = "F";
    public static final String POSITIVE_EQUIVALENT_MIN_LENGTH_CELL_STRING = "Fb";
    public static final String POSITIVE_BORDERED_MAX_LENGTH_CELL_STRING = "qwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasfasdf";
    public static final String POSITIVE_EQUIVALENT_MAX_LENGTH_CELL_STRING = "qwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasfasd";
    public static final String NEGATIVE_BORDERED_MAX_LENGTH_CELL_STRING = "fqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasfasdf";
    public static final String NEGATIVE_EQUIVALENT_MAX_LENGTH_CELL_STRING = "fqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasfasdffqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasqwertyuiohasdfxzgw1adgdasfasdf";
    public static final String NEGATIVE_BORDERED_MIN_LENGTH_CELL_STRING = " ";
    public static final String NEGATIVE_EQUIVALENT_MIN_LENGTH_CELL_STRING = "";
    public static final String SAMPLE_STRING = "sdfgh2ezvh";
    public static final String TEST_FILE_NAME = "SkillMatrixTest.xlsx";
    public static final String FIND_CELL_BY_NAME = "findCellByName";
    public static final String CREATE_PARENT_CELL = "createParentCell";
    public static final String CREATE_CHILD_CELL = "createChildCell";
    public static final String REMOVE_ROWS = "removeRows";
    public static List<Composite> emptyList = new ArrayList<>();
    public static XSSFWorkbook workbook = new XSSFWorkbook();
    public static XSSFSheet spreadsheet = workbook.createSheet("new sheet");
    public static XSSFRow row = spreadsheet.createRow(0);
    public static List<Cell> invalidCells = new ArrayList<>();
    static {
        XSSFCell one = row.createCell(0);
        one.setCellValue(EMPTY_STRING);
        one.setCellType(CellType.STRING);
        invalidCells.add(one);

        XSSFCell two = row.createCell(1);
        two.setCellValue(NEGATIVE_BORDERED_MAX_LENGTH_CELL_STRING);
        two.setCellType(CellType.STRING);
        invalidCells.add(two);

        XSSFCell three = row.createCell(2);
        three.setCellValue(SAMPLE_STRING);
        three.setCellType(CellType.FORMULA);
        invalidCells.add(three);

        XSSFCell four = row.createCell(3);
        four.setCellValue(NEGATIVE_EQUIVALENT_MAX_LENGTH_CELL_STRING);
        four.setCellType(CellType.STRING);
        invalidCells.add(four);


        XSSFCell five = row.createCell(4);
        five.setCellValue(NEGATIVE_EQUIVALENT_MIN_LENGTH_CELL_STRING);
        five.setCellType(CellType.STRING);
        invalidCells.add(five);

        XSSFCell six = row.createCell(4);
        six.setCellValue(NEGATIVE_BORDERED_MIN_LENGTH_CELL_STRING);
        six.setCellType(CellType.STRING);
        invalidCells.add(six);

        invalidCells.add(null);
    }

    public static List<Cell> validCells = new ArrayList<>();
    static {
        XSSFCell one = row.createCell(0);
        one.setCellValue(POSITIVE_BORDERED_MIN_LENGTH_CELL_STRING);
        one.setCellType(CellType.STRING);
        validCells.add(one);

        XSSFCell two = row.createCell(1);
        two.setCellValue(POSITIVE_BORDERED_MAX_LENGTH_CELL_STRING);
        two.setCellType(CellType.STRING);
        validCells.add(two);

        XSSFCell three = row.createCell(2);
        three.setCellValue(SAMPLE_STRING);
        three.setCellType(CellType.STRING);
        validCells.add(three);

        XSSFCell four = row.createCell(3);
        four.setCellValue(POSITIVE_EQUIVALENT_MAX_LENGTH_CELL_STRING);
        four.setCellType(CellType.STRING);
        validCells.add(four);


        XSSFCell five = row.createCell(4);
        five.setCellValue(POSITIVE_EQUIVALENT_MIN_LENGTH_CELL_STRING);
        five.setCellType(CellType.STRING);
        validCells.add(five);
    }

    public static List<Composite> compositeList = new ArrayList<>();
    static {
        Composite first = COMPOSITE_FACTORY.create("One");
        compositeList.add(first);
        Composite second = COMPOSITE_FACTORY.create("Two");
        compositeList.add(second);
        Composite third = COMPOSITE_FACTORY.create("Three");
        Composite fourth = COMPOSITE_FACTORY.create("Three");
        third.addComponent(fourth);
        compositeList.add(third);

    }

    public static List<JsonDto> validCreatingCriteria = new ArrayList<>();
    static {
        CreatingCriteria one = CREATING_CRITERIA_FACTORY.create("One", "One-Child");
        validCreatingCriteria.add(one);
        CreatingCriteria two = CREATING_CRITERIA_FACTORY.create("Two", "One-Child");
        validCreatingCriteria.add(two);
        CreatingCriteria three = CREATING_CRITERIA_FACTORY.create("Three", "One-Child");
        validCreatingCriteria.add(three);
    }

    public static List<JsonDto> invalidCreatingCriteria = new ArrayList<>();
    static {
        CreatingCriteria one = CREATING_CRITERIA_FACTORY.create("sample_name", "One-Child");
        invalidCreatingCriteria.add(one);
        CreatingCriteria two = CREATING_CRITERIA_FACTORY.create("is doesnt exist", "One-Child");
        invalidCreatingCriteria.add(two);
        CreatingCriteria three = CREATING_CRITERIA_FACTORY.create("randomName", "One-Child");
        invalidCreatingCriteria.add(three);
    }

    public static List<JsonDto> validUpdatingCriteria = new ArrayList<>();
    static {
        UpdatingCriteria one = UPDATING_CRITERIA_FACTORY.create("One", "One-Child");
        validUpdatingCriteria.add(one);
        UpdatingCriteria two = UPDATING_CRITERIA_FACTORY.create("Two", "One-Child");
        validUpdatingCriteria.add(two);
        UpdatingCriteria three = UPDATING_CRITERIA_FACTORY.create("Three", "One-Child");
        validUpdatingCriteria.add(three);
    }

    public static List<JsonDto> invalidUpdatingCriteria = new ArrayList<>();
    static {
        UpdatingCriteria one = UPDATING_CRITERIA_FACTORY.create("sample_name", "One-Child");
        invalidUpdatingCriteria.add(one);
        UpdatingCriteria two = UPDATING_CRITERIA_FACTORY.create("is doesnt exist", "One-Child");
        invalidUpdatingCriteria.add(two);
        UpdatingCriteria three = UPDATING_CRITERIA_FACTORY.create("randomName", "One-Child");
        invalidUpdatingCriteria.add(three);
    }

    public static List<JsonDto> validSearchingCriteria = new ArrayList<>();
    static {
        SearchingCriteria first = SEARCHING_CRITERIA_FACTORY.create("One");
        validSearchingCriteria.add(first);
        SearchingCriteria second = SEARCHING_CRITERIA_FACTORY.create("Two");
        validSearchingCriteria.add(second);
        SearchingCriteria third = SEARCHING_CRITERIA_FACTORY.create("Three");
        validSearchingCriteria.add(third);
        SearchingCriteria fourth = SEARCHING_CRITERIA_FACTORY.create("Three");
        validSearchingCriteria.add(fourth);
    }


    public static List<SearchingCriteria> invalidSearchingCriteria = new ArrayList<>();
    static {
        SearchingCriteria firstCriteria = SEARCHING_CRITERIA_FACTORY.create("124324");
        invalidSearchingCriteria.add(firstCriteria);
        SearchingCriteria secondCriteria = SEARCHING_CRITERIA_FACTORY.create("     ");
        invalidSearchingCriteria.add(secondCriteria);
        SearchingCriteria thirdCriteria = SEARCHING_CRITERIA_FACTORY.create("unknown_type");
        invalidSearchingCriteria.add(thirdCriteria);
    }

    public static List<Composite> validCompositeList = new ArrayList<>();
    static {
        Composite composite = COMPOSITE_FACTORY.create();
        Composite one = COMPOSITE_FACTORY.create("One");
        Composite two = COMPOSITE_FACTORY.create("Two");
        Composite three = COMPOSITE_FACTORY.create("Three");
        Composite four = COMPOSITE_FACTORY.create("Four");
        Composite five = COMPOSITE_FACTORY.create("Five");
        Composite six = COMPOSITE_FACTORY.create("Six");
        Composite seven = COMPOSITE_FACTORY.create("Seven");
        Composite eight = COMPOSITE_FACTORY.create("Eight");
        Composite nine = COMPOSITE_FACTORY.create("Nine");
        Composite ten = COMPOSITE_FACTORY.create("Ten");
        Composite eleven = COMPOSITE_FACTORY.create("Eleven");

        ten.addComponent(eleven);
        seven.addComponent(eight);
        four.addComponent(five);
        four.addComponent(six);
        four.addComponent(seven);
        three.addComponent(four);
        three.addComponent(nine);
        two.addComponent(three);
        composite.addComponent(one);
        composite.addComponent(two);
        composite.addComponent(ten);
        validCompositeList.add(composite);
    }

}
