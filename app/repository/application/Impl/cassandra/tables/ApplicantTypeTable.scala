package repository.application.Impl.cassandra.tables

import com.datastax.driver.core.Session
import com.outworkers.phantom.Table
import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.keys.PartitionKey
import domain.application.ApplicantType

abstract class ApplicantTypeTable extends Table[ApplicantTypeTable,ApplicantType]{

  object id extends StringColumn with PartitionKey

  object key extends StringColumn

  object sender extends StringColumn



}

  
