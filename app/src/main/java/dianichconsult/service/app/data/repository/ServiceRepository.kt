package dianichconsult.service.app.data.repository

import dianichconsult.service.app.R
import dianichconsult.service.app.data.model.ServiceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalTime

class ServiceRepository {
    private val services: List<ServiceModel> = listOf(
        ServiceModel(
            id = 1,
            name = "Security Audit",
            description = "Comprehensive assessment of your IT infrastructure to identify vulnerabilities, misconfigurations, and compliance gaps. Our certified auditors evaluate network architecture, access controls, and data handling practices to deliver actionable recommendations that strengthen your security posture.",
            price = 2500.0,
            category = "Cybersecurity",
            features = listOf("Network vulnerability scanning", "Access control review", "Security policy assessment", "Compliance gap analysis", "Executive summary report"),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.svc_security_audit,
        ),
        ServiceModel(
            id = 2,
            name = "Data Protection & GDPR",
            description = "End-to-end GDPR compliance consulting to protect personal data and avoid regulatory penalties. We map data flows, implement privacy-by-design principles, conduct Data Protection Impact Assessments, and establish robust breach notification procedures.",
            price = 3200.0,
            category = "Cybersecurity",
            features = listOf("Data flow mapping", "Privacy impact assessment", "Breach notification procedures", "Staff awareness training", "DPO advisory services"),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(13, 0), LocalTime.of(15, 0)),
            imageRes = R.drawable.svc_data_protection,
        ),
        ServiceModel(
            id = 3,
            name = "Penetration Testing",
            description = "Simulated cyber attacks conducted by ethical hackers to uncover exploitable weaknesses before malicious actors do. We test web applications, APIs, internal networks, and cloud environments using industry-standard methodologies including OWASP and PTES.",
            price = 4500.0,
            category = "Cybersecurity",
            features = listOf("Web application testing", "API security assessment", "Internal network testing", "Social engineering simulation", "Detailed remediation roadmap"),
            availableTime = listOf(LocalTime.of(10, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.svc_pen_testing,
        ),
        ServiceModel(
            id = 4,
            name = "Cloud Migration",
            description = "Strategic planning and hands-on execution of workload migration to AWS, Azure, or Google Cloud. We assess application readiness, design target architecture, manage data transfer, and validate performance to ensure zero-downtime transitions.",
            price = 5000.0,
            category = "Cloud",
            features = listOf("Cloud readiness assessment", "Migration strategy design", "Data transfer management", "Performance validation", "Post-migration support"),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.svc_cloud_migration,
        ),
        ServiceModel(
            id = 5,
            name = "Multi-Cloud Strategy",
            description = "Architect resilient multi-cloud environments that leverage the strengths of multiple providers while avoiding vendor lock-in. We design unified governance frameworks, networking topologies, and disaster recovery plans across AWS, Azure, and GCP.",
            price = 4200.0,
            category = "Cloud",
            features = listOf("Multi-provider architecture", "Vendor lock-in prevention", "Unified governance framework", "Cross-cloud networking", "Disaster recovery planning"),
            availableTime = listOf(LocalTime.of(10, 0), LocalTime.of(13, 0), LocalTime.of(16, 0)),
            imageRes = R.drawable.svc_multi_cloud,
        ),
        ServiceModel(
            id = 6,
            name = "Cloud Cost Optimization",
            description = "Reduce cloud spend by up to 40% without sacrificing performance. We analyze resource utilization, identify idle assets, implement right-sizing recommendations, and establish FinOps practices for ongoing cost governance.",
            price = 1800.0,
            category = "Cloud",
            features = listOf("Resource utilization analysis", "Right-sizing recommendations", "Reserved instance planning", "FinOps framework setup", "Monthly savings reporting"),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(12, 0), LocalTime.of(15, 0)),
            imageRes = R.drawable.svc_cost_optimization,
        ),
        ServiceModel(
            id = 7,
            name = "Digital Transformation",
            description = "Accelerate business growth through technology-driven change. We assess digital maturity, define transformation roadmaps, select enabling platforms, and guide implementation of modern architectures including microservices, APIs, and event-driven systems.",
            price = 6000.0,
            category = "Business",
            features = listOf("Digital maturity assessment", "Transformation roadmap", "Platform selection advisory", "Change management support", "ROI measurement framework"),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.svc_digital_transformation,
        ),
        ServiceModel(
            id = 8,
            name = "IT Audit & Governance",
            description = "Independent review of IT controls, processes, and governance structures aligned with ISO 27001, COBIT, and ITIL frameworks. We evaluate IT risk management, change control, and service delivery to ensure operational excellence and regulatory compliance.",
            price = 3500.0,
            category = "Business",
            features = listOf("IT controls assessment", "Risk management review", "ISO 27001 alignment", "COBIT framework evaluation", "Governance improvement plan"),
            availableTime = listOf(LocalTime.of(10, 0), LocalTime.of(13, 0), LocalTime.of(15, 0)),
            imageRes = R.drawable.svc_it_audit,
        ),
        ServiceModel(
            id = 9,
            name = "Process Automation",
            description = "Streamline operations with intelligent automation using RPA, workflow orchestration, and AI-powered document processing. We identify high-value automation opportunities, build scalable bots, and measure productivity gains to deliver rapid ROI.",
            price = 3800.0,
            category = "Business",
            features = listOf("Process discovery & mapping", "RPA bot development", "Workflow orchestration", "AI document processing", "Productivity measurement"),
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.svc_process_automation,
        ),
    )

    fun observeAll(): Flow<List<ServiceModel>> {
        return flowOf(services)
    }

    fun observeById(id: Int): Flow<ServiceModel?> {
        val service = services.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): ServiceModel? {
        return services.firstOrNull { service -> service.id == id }
    }
}
