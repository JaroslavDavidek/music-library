package cz.fi.muni.pa165;

import javax.sql.DataSource;
import org.eclipse.persistence.jpa.PersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 *
 * @author JaroslavDavidek
 */
@Configuration
public class MusicLibraryPersistenceContext {
    
    @Bean 
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean emFactoryBean = new LocalContainerEntityManagerFactoryBean();
        emFactoryBean.setPersistenceProviderClass(PersistenceProvider.class);
        emFactoryBean.setDataSource(db()); 
        return emFactoryBean;
     }
    
    @Bean
    public DataSource db(){
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.DERBY).build();
    }
}
