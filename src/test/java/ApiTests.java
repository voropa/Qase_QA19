import adapters.ProjectAdapter;
import com.google.gson.Gson;
import models.Project;
import models.ProjectResponse;
import models.Result;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {

    private final String expectedJson = "{\"status\":true,\"result\":{\"total\":3,\"filtered\":3,\"count\":3,\"entities\":[{\"title\":\"Demo Project\",\"code\":\"DEMO\",\"counts\":{\"cases\":10,\"suites\":3,\"milestones\":2,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}},{\"title\":\"TMS Demo\",\"code\":\"TD\",\"counts\":{\"cases\":2,\"suites\":2,\"milestones\":0,\"runs\":{\"total\":2,\"active\":0},\"defects\":{\"total\":2,\"open\":0}}},{\"title\":\"QA19\",\"code\":\"QA\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}}]}}";

    private final static Gson gson = new Gson();
    ProjectAdapter projectAdapter = new ProjectAdapter();

    @Test
    public void getAllProjectsTest() {
        String responseBody = projectAdapter.getAllProjects(200);
        Assert.assertEquals(responseBody, expectedJson);
    }

    @Test
    public void createProjectTest() {
        String testCode = "CODE3";

        Project project = Project.builder()
                .title("ADFDSFDSF")
                .code(testCode)
                .description("efsfsdfsdfsdf")
                .build();

        ProjectResponse expectedProjectResponseBody = ProjectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testCode)
                        .build())
                .build();

        String actualResponseBody = projectAdapter.createProject(200, gson.toJson(project));
        Assert.assertEquals(gson.fromJson(actualResponseBody, ProjectResponse.class),
                expectedProjectResponseBody);
    }
}
