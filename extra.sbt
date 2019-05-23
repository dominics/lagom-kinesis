import com.amazonaws.auth.AWSCredentialsProviderChain
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import fm.sbt.S3URLHandler

ThisBuild / version := "2.0.0-vital-SNAPSHOT"

ThisBuild / publishTo := {
  val repo = "s3://vital-repo.s3-us-west-2.amazonaws.com/maven/"
  if (isSnapshot.value)
    Some("Snapshots" at repo + "snapshots")
  else
    Some("Releases" at repo + "releases")
}
ThisBuild / publishMavenStyle := true

s3CredentialsProvider := { bucket: String =>
  new AWSCredentialsProviderChain(
    new ProfileCredentialsProvider("profile vital-master"),
    new ProfileCredentialsProvider("default"),
    S3URLHandler.defaultCredentialsProviderChain(bucket)
  )
}
