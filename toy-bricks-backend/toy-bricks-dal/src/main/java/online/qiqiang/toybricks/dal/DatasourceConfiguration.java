package online.qiqiang.toybricks.dal;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * @author quince
 */
@Configuration
@EnableR2dbcRepositories(basePackages = "online.qiqiang.toybricks.dal.repository")
public class DatasourceConfiguration {
}
